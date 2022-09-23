from django.shortcuts import render

# Create your views here.

from django.shortcuts import HttpResponse,get_object_or_404
from django.views import View
from .serializers import  CustomerSerializer
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import authentication, permissions,status
from .models import Customer

class Index(View):
    def get(self, request):
        return HttpResponse("Heyyyyyyyy get", status=405)

    def post(self,request):
        return HttpResponse("Heyyyyyyyy post", status=405)

class CustomerData(APIView):
    def get(self, request):
        customer=Customer.objects.all();
        serializer=CustomerSerializer(customer,many=True)
        return Response(serializer.data)


    def post(self,request):
        return HttpResponse("Heyyyyyyyy post", status=405)