from rest_framework.views import APIView
from django.http import HttpResponse, JsonResponse, Http404
from django.contrib import messages
from django.shortcuts import render,redirect
from .models import *
from rest_framework import serializers, status
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
# @api_view(["GET"])
# def IndexSerializer(request):
#     object1 = freelancer_details.objects.all()
#     serializersdata = freelancer_detailsSerializers(object1,many=True)
#     return Response(serializersdata.data)

# @api_view(["POST"])
# def IndexPostSerializer(request):
#     # object1 = freelancer_details.objects.all()
#     serializersdata = freelancer_detailsSerializers(data=request.data)
#     if serializersdata.is_valid():
#         serializersdata.save()
#     return JsonResponse(serializersdata.data)
# #
# @api_view(["POST"])
# def updateSerializer(request,id):
#     object1 = freelancer_details.objects.get(id=id)
#     serializersdata = freelancer_detailsSerializers(instance=object1,data=request.data)
#     if serializersdata.is_valid():
#         serializersdata.save()
#     return JsonResponse(serializersdata.data)

# @api_view(["DELETE"])
# def deleteSerializer(request,id):
#     object1 = freelancer_details.objects.get(id=id)
#     object1.delete()
#     return JsonResponse('deleted in the database')

# class FreelancerViewSet(viewsets.ViewSet):
#     def list(self,request):
#         queryset= freelancer_details.objects.all()
#         serializer = freelancer_detailsSerializers(queryset,many=True)
#         return Response(serializer.data)
#
#     def retrieve(self,request,pk=None):
#         queryset =  freelancer_details.objects.all()
#         freelance = get_object_or_404(queryset,pk=pk)
#         serializer = freelancer_detailsSerializers(freelance)
#         return Response(serializer.data)
#
#     def create(self,request):
#         serializer = freelancer_detailsSerializers(data=request.data)
#         serializer.is_valid()
#         return Response(serializer.data)
#
#     def update(self,request,pk=None):
#         pass
#     def partial_update(self,request,pk=None):
#         pass
#
#     def destroy(self,request,pk=None):
#         pass

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


class freelancerRegister(APIView):
    def get(self, request,format=None):
        freelancer = freelancer_details.objects.all()
        serializer = freelancer_details_serializers(freelancer, many=True)
        return Response(serializer.data)

    def post(selfself, request, format=None):
        serializer = freelancer_details_serializers(data = request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class freelanceUpdate(APIView):
    def get_object(self, pk):
        try:
           return freelancer_details.objects.get(pk=pk)
        except freelancer_details.DoesNotExist :
            raise Http404

    def get(self,request, pk, format=None):
        customer = self.get_object(pk=pk)
        serializer = freelancer_details_serializers(customer)
        return Response(serializer.data)

    def put(self,request, pk, format=None):
        customer = self.get_object(pk=pk)
        serializer = freelancer_details_serializers(customer, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        customer = self.get_object(pk=pk)
        customer.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)