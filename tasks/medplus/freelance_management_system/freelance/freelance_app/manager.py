from rest_framework.exceptions import AuthenticationFailed
from .jwtauthetication import *
from .models import *
from rest_framework.response import Response

def freelancer_authentication(request):
    email = request.data['email_id']
    password = request.data['password']
    user = freelancer_details.objects.filter(email_id=email).first()

    if user is None:
        raise AuthenticationFailed('User not found')

    if user.password != password:
        raise AuthenticationFailed('Incorrect password!')

    # payload ={
    #     'passenger_id': user.passenger_id,
    #     'exp': datetime.datetime.utcnow()+datetime.timedelta(minutes=120),
    #     'iat': datetime.datetime.utcnow()
    # }

    access_token = create_access_token(user.email_id)
    refresh_token = create_refresh_token(user.email_id)

    UserToken.objects.create(
        email_id=user.email_id,
        token=refresh_token,
        expired_at=datetime.datetime.utcnow() + datetime.timedelta(days=7)
    )

    response = Response()
    response.set_cookie(key='refresh_token', value=refresh_token, httponly=True)
    response.data = {
        'access_token': access_token,
        'msg': "successfully logged in"
    }
    return response