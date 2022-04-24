#include <WiFi.h>
#include <HTTPClient.h>
#include <Arduino_JSON.h>
#include <ESP32Servo.h>

const char* ssid = "Gia linh";
const char* password =  "AAnxongdiia08122014";
const char* serverAddress = "http://45.119.212.43:5000/GetStatus";
unsigned long lastTime = 0, delayTime = 1000, sensorLastTime = 0, sensorDelayTime = 2000;
String statusSensor;
const int light = 14, fan = 26, door = 33, sensor = 19;
// set flag
bool fanStatusFlag = false, lightStatusFlag = false;
int doorStatusFlag = 0;
Servo myServo;


void setup() {
  pinMode(light, OUTPUT);
  pinMode(fan, OUTPUT);
  pinMode(door, OUTPUT);
  pinMode(sensor, INPUT);
  myServo.attach(door);
  Serial.begin(115200);
  // connect to wifi
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi..");
  } 
  Serial.println("Connected to the WiFi network");

  digitalWrite(light, returnStatus(lightStatusFlag));
  digitalWrite(fan, returnStatus(fanStatusFlag));
}


void loop() {
  if ((millis() - lastTime) > delayTime) {
    // Check Wifi connection
    if(WiFi.status() == WL_CONNECTED) {
      HTTPClient http;
      statusSensor = httpGETRequest(serverAddress);
      Serial.println(statusSensor);
      JSONVar myJson = JSON.parse(statusSensor);
      bool lightStatus = myJson["lightStatus"];
      bool fanStatus = myJson["fanStatus"];
      int doorStatus = myJson["doorStatus"];
      int sensorStatus = digitalRead(sensor);

      // show sensor status
      Serial.println(sensorStatus);

      if (sensorStatus == 1) {
        lightStatus = true;
        lightStatusFlag = true;
        sensorLastTime = millis();
      }
      
      // check light status
      if (millis() - sensorLastTime < sensorDelayTime) {
          digitalWrite(light, returnStatus(true));
      }
      else if (lightStatus != lightStatusFlag) {
        lightStatusFlag = lightStatus;
        digitalWrite(light, returnStatus(lightStatus));
      }
      // check fan status
      if (fanStatus != fanStatusFlag) {
        fanStatusFlag = fanStatus;
        digitalWrite(fan, returnStatus(fanStatus));
      }
      // check door number
      if (doorStatus != doorStatusFlag) {
        doorStatusFlag = doorStatus;
        myServo.write(doorStatus);
      }
    }
    lastTime = millis();
  }
}


String httpGETRequest(const char* serverName) {
  HTTPClient http;
  http.begin(serverName);
  int httpResponseCode = http.GET();
  String payload = "{}"; 
  if (httpResponseCode > 0) {
    Serial.print("HTTP Response code: ");
    Serial.println(httpResponseCode);
    payload = http.getString();
  }
  else {
    Serial.print("Error code: ");
    Serial.println(httpResponseCode);
  }
  http.end();
  return payload;
}


int returnStatus(bool status) {
  return status ? 1 : 0;
}
