from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework import status
from fpapp import serializer
from fpapp.models import  Course, User,Student, Teacher
from fpapp.serializer import  UserSerializer, StudentSerializer, TeacherSerializer, CourseSerializer
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from django.contrib.auth import authenticate,login,logout

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
    # def get(self, request):
    #     courses = Teacher.objects.all()
    #     serializer = TeacherSerializer(courses, many=True)
    #     print(serializer.data)
    #     return Response(serializer.data)

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


@api_view(["GET"])
def teacher_list(request):
   
    courses = Teacher.objects.all()
    serializer = TeacherSerializer(courses, many=True)
    return Response(serializer.data)

@api_view(['POST'])
def loginUser(request):

    if request.method=="POST":
        username = request.data.get("username")
        password = request.data.get("password")

        try:
            user = User.objects.get(username=username)
        except:
            return Response({"msg":"Rey nuv levvu poraa saami"})

        user= authenticate(request,username=username, password=password)

        if user is not None:
            login(request,user)
            return Response({'msg':"logged in"}, status=200)
        else:
            return Response({'msg':"password sakkaga type chey bey"})

    
    return Response({"msg":"not created"}, status=200)



class RegisterCourse(APIView):
    def get(self,rquest):
        sub =Course.objects.all()
        serializer=CourseSerializer(sub,many= True)
        return Response(serializer.data)


    def post(self, request):
        serializer= CourseSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            print(serializer.data)
            return Response(serializer.data)
            
        else:
            print("invalid")
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST) 

        



# class SubjectList(APIView):
#     def get(self,rquest):
#         sub =Subject.objects.all()
#         serializer=SubjectSerializer(sub,many= True)
#         return Response(serializer.data)

# class SubjectCreate(APIView):
#     def post(self,request):
#         serializer =SubjectSerializer(data=request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return serializer.data
#         else:
#             return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST) 
