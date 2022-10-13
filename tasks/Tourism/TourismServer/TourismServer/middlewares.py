import jwt
from django.utils.deprecation import MiddlewareMixin

from TourismServer.settings import SECRET_KEY
from bookingsapp.JwtAuthentication import decode_refresh_token, create_access_token


class JwtMiddleware(MiddlewareMixin):
    def process_response(self, request, response):
        access_token = request.COOKIES.get('access_token')
        try:
            payload = jwt.decode(access_token, SECRET_KEY, algorithms='HS256')
        except jwt.ExpiredSignatureError:
            refresh_token = request.COOKIES.get('refresh_token')
            user_id = decode_refresh_token(refresh_token)
            new_access_token = create_access_token(user_id)
            response.set_cookie(key=access_token, value=new_access_token)

