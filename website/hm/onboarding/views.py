import io
from django.core.mail import send_mail
from django.views.decorators.csrf import csrf_exempt

from django.http.response import JsonResponse
from login.serializers import *
from login.serializers import *
from rest_framework.parsers import JSONParser
from rest_framework.views import APIView ,Response
from rest_framework import status
import random



from login.models import*
@csrf_exempt
class logindata(APIView):
    def post(self, request, format=None):
        data = request.data
        print(data)
        serializer = emp_loin_serializers(data=applydata)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse('done', safe=False)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)