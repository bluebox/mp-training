import datetime
import jwt
from rest_framework import exceptions
from rest_framework.authentication import BaseAuthentication
from rest_framework.authentication import get_authorization_header

from .models import Employee


def create_access_token(emp_id):
    user = Employee.objects.get(emp_id=emp_id)
    return jwt.encode({
        'emp_id': emp_id,
        'exp': datetime.datetime.utcnow() + datetime.timedelta(seconds=600),
        'iat': datetime.datetime.utcnow()
    }, 'secret_access', algorithm='HS256')


def decode_access_token(token):
    try:
        payload = jwt.decode(token, 'secret_access', algorithms='HS256')
        return payload['emp_id']
    except Exception as e:
        raise exceptions.AuthenticationFailed('Unauthenticated')


def create_refresh_token(emp_id):
    user = Employee.objects.get(emp_id=emp_id)
    return jwt.encode({
        'emp_id': emp_id,
        'exp': datetime.datetime.utcnow() + datetime.timedelta(days=5),
        'iat': datetime.datetime.utcnow()
    }, 'secret_access', algorithm='HS256')


def decode_refresh_token(token):
    try:
        payload = jwt.decode(token, 'secret_access', algorithms='HS256')
        return payload['emp_id']
    except Exception as e:
        raise exceptions.AuthenticationFailed('Unauthenticated')


class JWTAuthentication(BaseAuthentication):

    def authenticate(self, request):
        auth = get_authorization_header(request).split()
        if auth and len(auth) == 1:
            raise exceptions.AuthenticationFailed('Invalid basic header. No credentials provided.')
        elif len(auth) > 2:
            raise exceptions.AuthenticationFailed('Invalid basic header. Credentials string should not contain spaces.')
        elif len(auth) == 2:
            token = auth[1].decode('utf-8')
            emp_id = decode_access_token(token)
            user = Employee.objects.get(pk=emp_id)
            return user

        raise exceptions.AuthenticationFailed('Unauthenticated')
