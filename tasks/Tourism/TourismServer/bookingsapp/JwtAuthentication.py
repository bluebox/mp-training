import jwt, datetime
from rest_framework import exceptions
from rest_framework.authentication import BaseAuthentication
from rest_framework.authentication import get_authorization_header

from TourismServer.settings import SECRET_KEY
from bookingsapp.models import User, UserToken


def create_access_token(id):
    user = User.objects.get(id=id)
    return jwt.encode({
        'user_id': id,
        'exp': datetime.datetime.utcnow() + datetime.timedelta(seconds=600),
        'iat': datetime.datetime.utcnow()
    }, SECRET_KEY, algorithm='HS256')


def decode_access_token(token):
    try:
        payload = jwt.decode(token, SECRET_KEY, algorithms='HS256')
        return payload['user_id']
    except Exception as e:
        raise exceptions.AuthenticationFailed('Unauthenticated')


def create_refresh_token(id):
    user = User.objects.get(id=id)
    return jwt.encode({
        'user_id': id,
        'exp': datetime.datetime.utcnow() + datetime.timedelta(days=5),
        'iat': datetime.datetime.utcnow()
    }, SECRET_KEY, algorithm='HS256')


def decode_refresh_token(token):
    try:
        payload = jwt.decode(token, SECRET_KEY, algorithms='HS256')
        return payload['user_id']        
    except Exception as e:
        raise exceptions.AuthenticationFailed('Unauthenticated')

class JWTAuthentication(BaseAuthentication):

    def authenticate(self, request):
        access_token = request.COOKIES.get('access_token')

        try:
            payload = jwt.decode(access_token, SECRET_KEY, algorithms='HS256')
            # return payload['user_id']
            user = User.objects.get(pk=payload['user_id'])
            return (user, None)
        except jwt.ExpiredSignatureError:
            refresh_token = request.COOKIES.get('refresh_token')
            user_id = decode_refresh_token(refresh_token)
            # if not UserToken.objects.filter(
            #     user_id=user_id,
            #     token=refresh_token,
            #     expired_at__gt=datetime.datetime.now(tz=datetime.timezone.utc)
            # ).exists():
            #     raise exceptions.AuthenticationFailed('Unauthenticated')
            new_access_token = create_access_token(user_id)
            request.COOKIES['access_token'] = new_access_token
            user = User.objects.get(pk=user_id)
            return (user, None)

        except Exception as e:
            raise exceptions.AuthenticationFailed('Unauthenticated')















        # user_id = decode_refresh_token(access_token)
            
        # auth = access_token.split()

        # if not auth or auth[0].lower() != b'basic':
        #     return None

        # if auth and len(auth) == 1:
        #     raise exceptions.AuthenticationFailed('Invalid basic header. No credentials provided.')
        # elif len(auth) > 2:
        #     raise exceptions.AuthenticationFailed('Invalid basic header. Credentials string should not contain spaces.')
        # elif len(auth) == 2:
        #     token = auth[1].decode('utf-8')
        #     user_id = decode_access_token(token)
        #     user = User.objects.get(pk=user_id)
        #     return (user, None)

        # raise exceptions.AuthenticationFailed('Unauthenticated')

