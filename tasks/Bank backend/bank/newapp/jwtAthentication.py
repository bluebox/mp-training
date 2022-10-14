import jwt, datetime
from rest_framework import exceptions
from rest_framework.authentication import BaseAuthentication
from rest_framework.authentication import get_authorization_header

# from .settings import SECRET_KEY
from .models import EmployesTable


def create_access_token(id):
    return jwt.encode({
        'emp_id': id,
        'exp': datetime.datetime.utcnow() + datetime.timedelta(seconds=300),
        'iat': datetime.datetime.utcnow()
    }, "BANK", algorithm='HS256')


def decode_access_token(token):
    try:
        payload = jwt.decode(token, "BANK", algorithms='HS256')
        return payload['emp_id']
    except Exception as e:
        print("Autho", e)
        raise exceptions.AuthenticationFailed('Unauthenticated')


def create_refresh_token(id):
    return jwt.encode({
        'emp_id': id,
        'exp': datetime.datetime.utcnow() + datetime.timedelta(days=5),
        'iat': datetime.datetime.utcnow()
    }, 'refresh_secret', algorithm='HS256')


def decode_refresh_token(token):
    try:
        payload = jwt.decode(token, 'refresh_secret', algorithms='HS256')
        return payload['emp_id']
    except Exception as e:
        print(e)
        raise exceptions.AuthenticationFailed('Unauthenticated')

class JWTAuthentication(BaseAuthentication):

    def authenticate(self, request):

        auth = get_authorization_header(request).split()

        # if not auth or auth[0].lower() != b'basic':
        #     return None

        if auth and len(auth) == 1:
            raise exceptions.AuthenticationFailed('Invalid basic header. No credentials provided.')
        elif len(auth) > 2:
            raise exceptions.AuthenticationFailed('Invalid basic header. Credentials string should not contain spaces.')
        elif len(auth) == 2:
            token = auth[1].decode('utf-8')
            print("Decoding", auth[1].decode('utf-8'))
            user_id = decode_access_token(token)
            print(id)

            user = EmployesTable.objects.get(pk="employee_id")
            return (user, None)

        raise exceptions.AuthenticationFailed('Unauthenticated')