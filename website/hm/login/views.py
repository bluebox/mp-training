
from .models import*
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from django.http.response import JsonResponse
from .serializers import *
from  rest_framework.views import APIView




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
            print(data[0])
            print(type(data))
            datain = apply_candiate_login.objects.filter(loginid=data[1]['mail']).values()
            print(data,datain)
            if data[0]['password']==datain[0]['password']:

              return JsonResponse('True', safe=False)

            else :
              return JsonResponse('False', safe=False)


class candiatelogsssin(APIView):
    def post(self,request,format=None):
            data=request.data
            print(type(data))
            datain = apply_candiate_login.objects.filter(loginid=data[0]['mail']).values()
            print(data,datain[0]['password'])


            return JsonResponse(datain[0]['password'], safe=False)