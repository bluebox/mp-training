import io
from django.core.mail import send_mail
from django.views.decorators.csrf import csrf_exempt

from django.http.response import JsonResponse
from .serializers import *
from login.serializers import *
from rest_framework.parsers import JSONParser
from rest_framework.views import APIView ,Response
from rest_framework import status
import random



@csrf_exempt
def vacancy(request):
    if request.method == 'GET':
        print("hello")
        data=vaacancy.objects.all()
        data_s=vacancy_serializers(data,many=True)
        return JsonResponse(data_s.data,safe=False)


        return JsonResponse("Error" , safe=False)


@csrf_exempt

def decider(request):

    if request.method=='POST':
        jsondata=request.body
        stream=io.BytesIO(jsondata)
        userdata=JSONParser().parse(stream)

        mail=userdata['mail']
        print(mail[-10:])
        data=candidate_info.objects.filter(mail=mail).values()

        if mail[-10:]!="@gmail.com":
            return JsonResponse('invalid',safe=False)

        elif len(data)==0:
            data={'message':'signup',
                  'otp':str(random.randint(1111,9999))
            }
            send_mail('otp','OTP:-' +data['otp'] +'otp will expire in 2 min','hightechotp@gmail.com',[mail])
            return JsonResponse(data,safe=False)

        elif len(data)==1:

            return JsonResponse('login',safe=False)



class apply(APIView):
    def post(self,request,format=None):
        data=request.data
        print(data)

        cid=candidate_info.objects.filter(mail=data[1]).values()
        print(type(cid),cid)
        applydata = {
            "cid":cid[0]['cid'],
            "vid":data[0]
        }

        serializer=applying_serializers(data=applydata)

        if serializer.is_valid() :
            serializer.save()

            return JsonResponse('done', safe=False)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)


class signup(APIView):
    def post(self,request,format=None):
        data=request.data
        cid=candidate_info.objects.all()
        data['cid']=len(cid)
        logindata = {
            "cid":data['cid'],
            "loginid":data['mail'],
            "password":data['password']

        }

        serializer=candidate_info_serializers(data=data)
        lserializer=apply_candiate_login_serializers(data=logindata)
        print(data,logindata)

        if serializer.is_valid() :
            serializer.save()
            if lserializer.is_valid():
               lserializer.save()
               return JsonResponse('done', safe=False)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)



#






