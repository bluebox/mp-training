
from .models import*
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from django.http.response import JsonResponse
from rest_framework.response import Response
from .serializers import *
from  rest_framework.views import APIView
from rest_framework_simplejwt.tokens import RefreshToken
from rest_framework_simplejwt.backends import TokenBackend
from rest_framework_simplejwt.serializers import TokenVerifySerializer
import jwt

def get_token(user):
    refresh=RefreshToken.for_user(User)
    return {
        'refresh':str(refresh),
        'access':str(refresh.access_token)
    }




@csrf_exempt
def emplogin(request):
    if request.method=='GET':
        dat=emp_login.objects.all()
        dat_s=apply_candiate_login_serializers(dat,many=True)
        return JsonResponse(dat_s.data,safe=False)

    elif request.method =='POST':
        print(request)
        dat=JSONParser().parse(request)
        dat_s=apply_candiate_login_serializers(data=dat)
        print(dat_s)
        if dat_s.is_valid():
            print("hello")
            dat_s.save()
            return JsonResponse("Registered",safe=False)
        return JsonResponse("Error" , safe=False)


class candiatelogin(APIView):
    def post(self,request,format=None):
            data=request.data
            print(data)
            print(data['mail'])
            #print(data[0])
            print(type(data))
            datain = apply_candiate_login.objects.filter(loginid=data['mail']).values()
            print(data,datain)
            if data['password']==datain[0]['password']:
         #     token=get_token(data)
              token=jwt.encode(data,'secret',algorithm='HS256').decode('utf-8')
              re={
                  'result':'True',
                  'token':token

              }
              response=Response()
              response.set_cookie(key='jwt',value=token,httponly=True)
              response.data={'jwt':token}
              print(response.data)
              return JsonResponse(token, safe=False)

            else :
              return JsonResponse('False', safe=False)


# class candiatelogin(APIView):
#     def post(self,request,format=None):
#             data=request.data
#             print(data)
#             print(data[1]['mail'])
#             #print(data[0])
#             print(type(data))
#             datain = apply_candiate_login.objects.filter(loginid=data[1]['mail']).values()
#             print(data,datain)
#             if data[0]['password']==datain[0]['password']:
#               print(data[0]['password'],datain[0]['password'])
#               token=get_token(data)
#               re={
#                   'result':'True',
#                   'token':token
#
#               }
#               response=Response()
#               response.set_cookie(key='jwt',value=token,httponly=True)
#               response.data={'jwt':token}
#               return JsonResponse(token, safe=False)
#
#             else :
#               return JsonResponse('False', safe=False)




class verify(APIView):
    def get(self,request):
        data=request.data
        try:
         payload = jwt.decode(data['token'], 'secret', algorithm=['HS256']);
         print(payload)
         return JsonResponse('True',safe=False)
        except:
            return JsonResponse('False', safe=False)

