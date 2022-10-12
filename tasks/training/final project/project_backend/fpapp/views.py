from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework import status
from fpapp import serializer
from fpapp.models import  Course, Question, User,Student, Teacher
from fpapp.serializer import  QuestionSerializer, QuestionSerializer2, UserSerializer, StudentSerializer, TeacherSerializer, CourseSerializer
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from django.contrib.auth import authenticate,login,logout
from django.http.response import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from rest_framework.decorators import api_view

# Create your views here.

def index(request):
    return HttpResponse("welcome")

class RegisterStudent(APIView):
    def post(self,request):
        print(request.data)
      
        serializer = UserSerializer(data={'username': request.data["username"],"first_name":request.data["first_name"],'last_name':request.data["last_name"],'email':request.data['email'],"mobile_no":request.data["mobile_no"]
        ,'address':request.data['address'], "password":request.data['password'],"user_type":"Student"})
        print(serializer)
        if serializer.is_valid():
            user = serializer.save()
            print(user.user_type)
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
        # print(request.data)
        
        serializer = UserSerializer(data={'username': request.data["username"],"first_name":request.data["first_name"],'last_name':request.data["last_name"],'email':request.data['email'],"mobile_no":request.data["mobile_no"]
        ,'address':request.data['address'], "password":request.data['password'],"user_type":'Teacher'})
        
        
        # print(serializer.data)
        if serializer.is_valid():
            
            user = serializer.save()
            
            print(user.user_type)
        
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
    teachers = Teacher.objects.all()
    serializer = TeacherSerializer(teachers, many=True)
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
            # all=UserSerializer(instance=user, many=False)

            return Response({'msg':"logged in", 'user':user.username, 'user_type':user.user_type}, status=200)
            
        else:
            return Response({'msg':"password sakkaga type chey bey"})

    
    return Response({"msg":"not created"}, status=200)



class RegisterCourse(APIView):

    def get(self,request):
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

class AddCourse(APIView):
    def post(self,request):
        print(request.data)
        data=request.data.get('form')
        username=request.data.get('username')
        print(data)
        print(username)
        user=User.objects.get(username=username)
        print(user)
        data['teacher_id']=user.id
        print()
        regi=CourseSerializer(data=data)
        print(regi)
        if regi.is_valid():
            regi.save()
            return Response({"message": "Course Added"}, status=200)
        else:
            return Response({"message": "Course not added"}, status=500)
        




@api_view(["GET"])
def course_list(request):
    courses = Course.objects.all()
    serializer = CourseSerializer(courses, many=True)
    return Response(serializer.data)


class RegisterQuestion(APIView):
    def get(self,request):
        sub =Question.objects.all()
        serializer=QuestionSerializer(sub,many= True)
        return Response(serializer.data)
    
    def post(self, request):
        print(request.data)
        serializer= QuestionSerializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            print(serializer.data)
            return Response(serializer.data)
        else:
            print("invalid")
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST) 


@csrf_exempt
def delete_course(request):
    data = JSONParser().parse(request)
    ele= Course.objects.get(id=data['id'])
    ele.delete()
    return JsonResponse("Deleted", safe=False)


@csrf_exempt
def delete_question(request):
    data = JSONParser().parse(request)
    print(data)
    elections = Question.objects.get(id=data['id'])
    elections.delete()
    return JsonResponse("Deleted", safe=False)
        

class DisplayQuestion(APIView):
    def get(self,request):
        items = []
        for i in Question.objects.all():
            items.append({
            "question_name":i.question_name,
            "course_name":i.course.course_name
            
            })
        # items = Question.objects.all().values("question_name", 'course__course_name')
        print(items)
        return Response(items)


class AttemptExam(APIView):
     def get(self,request):
        items = []
        data=Question.objects.all()
        serializer=QuestionSerializer2(data, many=True)
        for i in serializer.data:
            print(i['question_name'])
            for j in i:
                print(j)
            
        print(serializer.data[1])
        return Response(serializer.data)
 

class StartExam(APIView):
    
    def post(self,request):
        print(request.data)
        data=request.data.get('course_id')
        sub=Question.objects.filter(course=data)
        print(len(sub))
        serializer=QuestionSerializer(sub, many=True)
        print(sub)
        print(serializer.data)

        return Response(serializer.data)



