"""to handle authentication"""
import datetime
from  rest_framework import exceptions
import jwt

def create_access_token(_id):
    """create access token"""
    return jwt.encode({
        'user_id' : _id,
        'exp' : datetime.datetime.utcnow() + datetime.timedelta(days=10),
        'iat' : datetime.datetime.utcnow()
    }, 'access_secret_key', algorithm='HS256')


def create_refresh_token(_id):
    """create refresh token"""
    return jwt.encode({
        'user_id' : _id,
        'exp' : datetime.datetime.utcnow() + datetime.timedelta(days=5),
        'iat' : datetime.datetime.utcnow()
    }, 'refresh_secret_key', algorithm='HS256')


def decode_access_token(token):
    """decode access token"""
    try:
        payload = jwt.decode(token, 'access_secret_key', algorithms='HS256')
        return payload['user_id']
    except Exception:
        raise exceptions.AuthenticationFailed('unauthenticated')


def decode_refresh_token(token):
    """decode refresh token and return user id"""
    try:
        payload = jwt.decode(token, 'refresh_secret_key', algorithms='HS256')
        return payload['user_id']
    except:
        raise exceptions.AuthenticationFailed('unauthenticated')
