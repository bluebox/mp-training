
from django.http import HttpResponse,JsonResponse
from django.contrib import messages
from django.shortcuts import render,redirect
from .models import *
from rest_framework import serializers
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .forms import  FreelanceForm
from rest_framework.viewsets import ViewSet
from rest_framework import viewsets
from rest_framework import  generics
from .serializers import  *
from rest_framework.generics import ListAPIView,GenericAPIView
# Create your views here.

from django.shortcuts import  get_object_or_404
@api_view(["GET"])
def IndexSerializer(request):
    object1 = freelancer_details.objects.all()
    serializersdata = freelancer_detailsSerializers(object1,many=True)
    return Response(serializersdata.data)

@api_view(["POST"])
def IndexPostSerializer(request):
    # object1 = freelancer_details.objects.all()
    serializersdata = freelancer_detailsSerializers(data=request.data)
    if serializersdata.is_valid():
        serializersdata.save()
    return Response(serializersdata.data)

@api_view(["POST"])
def updateSerializer(request,id):
    object1 = freelancer_details.objects.get(id=id)
    serializersdata = freelancer_detailsSerializers(instance=object1,data=request.data)
    if serializersdata.is_valid():
        serializersdata.save()
    return Response(serializersdata.data)

@api_view(["DELETE"])
def deleteSerializer(request,id):
    object1 = freelancer_details.objects.get(id=id)
    object1.delete()
    return Response('deleted in the database')

# @api_view()
# class freelanceViewSet(ViewSet):
#     object1 = freelancer_details.objects.all()
#     def list(self,request):
#         serializers1 = freelancer_detailsSerializers(self.object1,many=True)
#         return Response(serializers1.data)
#     def retrieve(self,request,pk=None):
#         item = get_object_or_404(self.object1,pk=pk)
#         serializers = freelancer_detailsSerializers(item)
#         return Response(serializers.data)
#
# class freelanceViewSet1(viewsets.ModelViewSet):
#     object1 = freelancer_details.objects.all()
#     serializersdata = freelancer_detailsSerializers

# # global id1
# id1 = 100
#
#
# def func():
#     global id1
#     id1 += 1
# def freelance_details_page(request):
#     # global id1
#     if request.method == "POST":
#
#         email_id1= request.POST.get('email_id')
#         if freelancer_details.objects.filter(email_id=email_id1).exists():
#             messages.error(request,"email id already exists")
#             return redirect('/')
#         else:
#             first_name1= request.POST.get('first_name')
#             last_name1= request.POST.get('last_name')
#             phone_number1= request.POST.get('phone_number')
#             country1= request.POST.get('country')
#             password1 = request.POST.get("password")
#             object1 = freelancer_details(id=id1,first_name=first_name1,last_name=last_name1,email_id=email_id1,phone_number=phone_number1,password=password1,country=country1)
#             object1.save()
#             func()
#             return HttpResponse("details added successfully")
#     else:
#         return HttpResponse("djsfj")
#
#
# def loginpage(request):
#     if request.method == "POST":
#         value = request.POST.get('value')
#         if value ==1:
#             email_id1 = request.POST.get('email_id')
#             if freelancer_details.objects.filter(email_id=email_id1).exists():
#                 return render(request,'freelance_first.html')
#             else:
#                 messages.error(request,'invaild email or password')
#                 return redirect('/')
#         else:
#             email_id1 = request.POST.get('email_id')
#             if client_details.objects.filter(email_id=email_id1).exists():
#                 return render(request,'client_first.html')
#             else:
#                 messages.error(request,'invaild email or password')
#                 return redirect('/')
#
