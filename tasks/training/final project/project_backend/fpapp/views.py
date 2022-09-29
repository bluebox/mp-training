from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework import status
from fpapp.models import User,Student, Teacher
from fpapp.serializer import UserSerializer, StudentSerializer, TeacherSerializer
from rest_framework.views import APIView

# Create your views here.

def index(request):
    return HttpResponse("welcome")

class RegisterStudent(APIView):
    def post(self,request):
        print(request.data)
      
        serializer = UserSerializer(data={'username': request.data["username"],"first_name":request.data["first_name"],'last_name':request.data["last_name"],'email':request.data['email'],"mobile_no":request.data["mobile_no"]
        ,'address':request.data['address'], "password":request.data['password']})
        print(serializer)
        if serializer.is_valid():
            user = serializer.save()
            student_obj = StudentSerializer(data = {"user":user.id ,"reqister_number":request.data["reqister_number"] , "college_name":request.data['college_name'] })

            if student_obj.is_valid():
                student_obj.save()
            else:
                return Response(student_obj.errors, status=status.HTTP_400_BAD_REQUEST)   
            return Response(student_obj.data,status=200)
        else:
            print('invalid')
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class RegisterTeacher(APIView):
    def post(self,request):
        print(request.data)
        serializer = UserSerializer(data={'username': request.data["username"],"first_name":request.data["first_name"],'last_name':request.data["last_name"],'email':request.data['email'],"mobile_no":request.data["mobile_no"]
        ,'address':request.data['address'], "password":request.data['password']})
        print(serializer)
        if serializer.is_valid():
            user = serializer.save()
            teacher_obj = TeacherSerializer(data = {"user":user.id ,"qualification":request.data["qualification"] , "position":request.data["position"] })

            if teacher_obj.is_valid():
                teacher_obj.save()
            else:
                return Response(teacher_obj.errors, status=status.HTTP_400_BAD_REQUEST)   
            return Response(teacher_obj.data,status=200)
        else:
            print('invalid')
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)  

