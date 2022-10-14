from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.exceptions import AuthenticationFailed
from .serializers import *
from .models import User
import jwt, datetime
from cloudinary.uploader import upload


class Signup(APIView):
    def post(self, request):
        serializer = UserSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data)


class upload_file(APIView):
    def post(self,request):
        temp=upload(request.data.get('image'))
        return Response(temp['secure_url'])



class Login(APIView):
    def post(self, request):
        email_id = request.data['email_id']
        password = request.data['password']
        user = User.objects.filter(email_id=email_id).first()

        if user is None:
            raise AuthenticationFailed('User not their')



        if not user.check_password(password):
            raise AuthenticationFailed('incorrect password!')

        payload_Data = {
            'iat': datetime.datetime.utcnow(),
            'id': user.id,
            'exp': datetime.datetime.utcnow() + datetime.timedelta(days=1),
        }

        token = jwt.encode(payload_Data, 'secret',algorithm='HS256').decode('utf-8')
        response = Response()


        response.set_cookie(key='jwt',value=token, httponly=True)
        response.data = {
            'jwt': token
        }
        return response

                
class malik(APIView):
    def get(self,request,id):
        user=User.objects.get(id=id)
        serializer = UserSerializer(user)
        return Response(serializer.data)

class Edit_Profile(APIView):
    def put(self,request):
        user=User.objects.get(id=request.data.get('id'))
        if request.data.get('u_name'):
            user.u_name=request.data.get('u_name')
        if request.data.get('f_name'):  
            user.f_name=request.data.get('f_name')
        if request.data.get('l_name'):
            user.l_name=request.data.get('l_name')
        if request.data.get('profile'):
            user.profile=request.data.get('profile')
        user.save()
        return Response({'msg':'success'})


class User_view(APIView):

    def get(self, request):
        token = request.COOKIES.get('jwt')
      
        if not token:
            raise AuthenticationFailed('not user')
        try:
            payload_Data = jwt.decode(token, 'secret',algorithm=['HS256'])
        except jwt.ExpiredSignatureError:
            raise AuthenticationFailed('not user')

        user = User.objects.filter(id=payload_Data['id']).first()
        serializer = UserSerializer(user)
        return Response(serializer.data)


class Logout(APIView):
    def post(self, request):
        response = Response()
        response.delete_cookie('jwt')
        response.data = {
            'message': 'success'
        }
        return response


