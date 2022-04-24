from flask import Flask, request, jsonify
import hashlib
import os
from dotenv import load_dotenv

app = Flask(__name__)
load_dotenv()
serrect_key = os.getenv('secret_key')
authen_token = 'Bearer ' + hashlib.sha256(serrect_key.encode('utf-8')).hexdigest()
light_status = False  # false is turn off, true is turn on
fan_status = False  # false is turn off, true is turn on
door_status = 0  # 180 is close, 30 is open
print('Authentication Token:', authen_token)


@app.route('/')
def hello_world():
    return 'API Code by NghiaPH!'


@app.route('/GetStatus', methods=['GET'])
def get_status():
    global light_status, fan_status, door_status
    return jsonify({
        'lightStatus': light_status,
        'fanStatus': fan_status,
        'doorStatus': door_status
    }), 200


@app.route('/UpdateStatus', methods=['POST'])
def update_status():
    global light_status, fan_status, door_status
    try:
        token = request.headers.get('AuthenToken')  # Authentication Token
        data = request.get_json()
        if ('lightStatus' in data) and ('fanStatus' in data) and ('doorStatus' in data) and token:
            light = data['lightStatus']
            fan = data['fanStatus']
            door = data['doorStatus']
            # check authentication token
            if token != authen_token:
                return jsonify({
                    'isError': True,
                    'message': 'Bad Request - Invalid authentication token.'
                }), 401
            # update status
            light_status = light
            fan_status = fan
            door_status = door
            return jsonify({
                'isError': False,
                'message': 'Update successful.'
            }), 200
        else:
            return jsonify({
                'isError': True,
                'message': 'Bad Request - Invalid credendtials.'
            }), 500
    except Exception as ex:
        return jsonify({
            'isError': True,
            'message': ex
        }), 500


if __name__ == '__main__':
    app.run(host='0.0.0.0')
