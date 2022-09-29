# from django.shortcuts import render
#
# from django.shortcuts import HttpResponse,get_object_or_404
# from django.views import View
#
# from rest_framework.views import APIView
# from rest_framework.response import Response
# from rest_framework.exceptions import AuthenticationFailed
# from rest_framework import authentication, permissions,status
# from home.models import Customer
# from rest_framework.parsers import JSONParser
# # Create your views here.
# class Login(APIView):
#     def post(self,request):
#         email=request.data['customer_email']
#         password=request.data['customer_password']
#         user=Customer.objects.filter(customer_email=email).first()
#
#         if user is None:
#             raise AuthenticationFailed('User not found! Try Again or register!')
#
#         if not user.check_password(password):
#             raise AuthenticationFailed('Wrong password!')
#
#         return Response(user)