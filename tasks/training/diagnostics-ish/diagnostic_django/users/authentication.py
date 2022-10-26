import jwt , datetime
from  rest_framework import exceptions

def create_access_token(id):
    return jwt.encode({
        'user_id' : id,
        'exp' : datetime.datetime.utcnow() + datetime.timedelta(days=10),
        'iat' : datetime.datetime.utcnow()
    },'access_secret_key' , algorithm = 'HS256').decode('utf-8')


def create_refresh_token(id):
    return jwt.encode({
        'user_id' : id,
        'exp' : datetime.datetime.utcnow() + datetime.timedelta(days=5),
        'iat' : datetime.datetime.utcnow()
    },'refresh_secret_key' , algorithm = 'HS256').decode('utf-8')


def decode_access_token(token):
    try:
        payload = jwt.decode(token,'access_secret_key' , algorithms = 'HS256')
        return payload['user_id']
    except Exception:
        raise exceptions.AuthenticationFailed('unauthenticated')


def decode_refresh_token(token):
    try:
        payload = jwt.decode(token , 'refresh_secret_key' ,  algorithms = 'HS256')
        return payload['user_id']
    except:
        raise exceptions.AuthenticationFailed('unauthenticated')

