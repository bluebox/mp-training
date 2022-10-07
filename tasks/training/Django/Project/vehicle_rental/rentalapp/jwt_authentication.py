import jwt, datetime
from rest_framework import exceptions
from rest_framework.authentication import BaseAuthentication
from rest_framework.authentication import get_authorization_header
from . models import Customer, Owner



def create_access_token(id):
    return jwt.encode({
        'id' : id,
        'exp' : datetime.datetime.utcnow() + datetime.timedelta(days=10),
        'iat' : datetime.datetime.utcnow()
    }, 'access_secret', algorithm='HS256')

def decode_access_token(token):
    try:
        payload = jwt.decode(token, 'access_secret', algorithms='HS256')
        return payload['id']
    except:
        raise exceptions.AuthenticationFailed('un authorised')

def create_refresh_token(id):
    return  jwt.encode({
        'id' : id,
        'exp' : datetime.datetime.utcnow() + datetime.timedelta(days=100),
        'iat' : datetime.datetime.utcnow()
     }, 'access_secret', algorithm='HS256' )

def decode_refresh_token(token):
    try:
        payload = jwt.decode(token, 'refresh_secret', algorithms='HS256')
        return payload['id']
    except Exception as e:
        print(e)
        raise exceptions.AuthenticationFailed('Unauthenticated')


class JwtAuthentication_owner(BaseAuthentication):
    def authenticate(self, request):
        auth = get_authorization_header(request).split()

        if auth and len(auth) == 1:
            raise exceptions.AuthenticationFailed('No credentials provided')
        elif len(auth) > 2:
            raise exceptions.AuthenticationFailed('Credentials string should not contain spaces')
        elif len(auth) == 2:
            token = auth[1].decode('utf-8')
            user_id = decode_access_token(token)
            owner = Owner.objects.get(pk=user_id)
            return (owner, None)

        raise exceptions.AuthenticationFailed('Unauthenticated')


class JwtAuthentication_customer(BaseAuthentication):
    def authenticate(self, request):
        auth = get_authorization_header(request).split()

        if auth and len(auth) == 1:
            raise exceptions.AuthenticationFailed('No credentials provided')
        elif len(auth) > 2:
            raise exceptions.AuthenticationFailed('Credentials string should not contain spaces')
        elif len(auth) == 2:
            token = auth[1].decode('utf-8')
            user_id = decode_access_token(token)
            customer = Customer.objects.get(customer_id=user_id)
            return (customer, None)

        raise exceptions.AuthenticationFailed('Unauthenticated')

