#pylint:disable=E1101
#pylint:disable=C0103
#pylint:disable=W0702
#pylint:disable=W0622
#pylint:disable=R1710
#pylint:disable=C0412
"""these are views"""
from rest_framework.response import Response
from rest_framework import status
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from django.contrib.auth import authenticate,login
from django.http.response import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from fpapp.models import  Course, Question, Score, User,Student, Teacher
from fpapp.serializer import  QuestionSerializer, QuestionSerializer1,\
    QuestionSerializer2, ScoreSerializer, UserSerializer, StudentSerializer,\
    TeacherSerializer, CourseSerializer


class RegisterStudent(APIView):
    """this is student register class"""
    def get(self,request):
        """this is get method"""
        print(request.data)
        sub=Student.objects.all()
        serializer=StudentSerializer(sub, many=True)
        return Response(serializer.data)
    def post(self,request):
        """this is docstring"""
        print(request.data)
        username=request.data.get("username")
        user=User.objects.filter(username=username).first()
        if user:
            return Response({"msg":"username already taken"})
        serializer = UserSerializer(data={'username': request.data["username"],
        "first_name":request.data["first_name"],'last_name':request.data["last_name"],
        'email':request.data['email'],"mobile_no":request.data["mobile_no"]
        ,'address':request.data['address'], "password":request.data['password'],
        "user_type":"Student"})
        print(serializer)
        if serializer.is_valid():
            user = serializer.save()
            print(user.user_type)
            student_obj = StudentSerializer(data = {"user":user.id ,
            "reqister_number":request.data["reqister_number"],
            "college_name":request.data['college_name'] })
            if student_obj.is_valid():
                student_obj.save()
            else:
                return Response(student_obj.errors, status=status.HTTP_400_BAD_REQUEST)
            return Response({"msg":"successful","student":student_obj.data},status=200)
        print('invalid')
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class RegisterTeacher(APIView):
    """this is docstring"""
    def post(self,request):
        """this is docstring"""
        print(request.data)
        username=request.data.get("username")
        user=User.objects.filter(username=username).first()
        if user:
            return Response({"msg":"username already taken"})
        serializer = UserSerializer(data={'username': request.data["username"],
        "first_name":request.data["first_name"],'last_name':request.data["last_name"],
        'email':request.data['email'],"mobile_no":request.data["mobile_no"],
        'address':request.data['address'], "password":request.data['password'],
        "user_type":'Teacher'})
        if serializer.is_valid():
            user = serializer.save()
            print(user.user_type)
            teacher_obj = TeacherSerializer(data = {"user":user.id,
            "qualification":request.data["qualification"],
            "position":request.data["position"]})

            if teacher_obj.is_valid():
                teacher_obj.save()
            else:
                return Response(teacher_obj.errors, status=status.HTTP_400_BAD_REQUEST)
            return Response({"msg":"successful","teacher":teacher_obj.data},status=200)
        print('invalid')
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(["GET"])
def teacher_list(request):
    """this is docstring"""
    print(request.data)
    teachers = Teacher.objects.all()
    serializer = TeacherSerializer(teachers, many=True)
    return Response(serializer.data)

@api_view(['POST'])
def loginUser(request):
    """this is docstring"""
    if request.method=="POST":
        username = request.data.get("username")
        password = request.data.get("password")

        try:
            user = User.objects.get(username=username)
        except:
            return Response({"msg":"username is not valid"})

        user= authenticate(request,username=username, password=password)

        if user is not None:

            login(request,user)

            return Response({'msg':"logged in",'user':user.username,'user_type':user.user_type},
            status=200)
        return Response({'msg':"password is incorrect"})
    return Response({"msg":"not created"}, status=200)


class RegisterCourse(APIView):
    """this is docstring"""
    def get(self,request):
        """this is docstring"""
        print(request.data)
        sub =Course.objects.all()
        serializer=CourseSerializer(sub,many= True)
        return Response(serializer.data)


    def post(self, request):
        """this is docstring"""
        serializer= CourseSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            print(serializer.data)
            return Response(serializer.data)
        print("invalid")
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class AddCourse(APIView):
    """this is docstring"""
    def post(self,request):
        """this is docstring"""
        print(request.data)
        data=request.data.get('form')
        username=request.data.get('username')
        course_name=request.data['form'].get('course_name')
        course=Course.objects.filter(course_name=course_name)
        print(data)
        print(course_name)
        if course:
            return Response({"message":"course already exists"})
        user=User.objects.get(username=username)
        print(user)
        data['teacher_id']=user.id
        regi=CourseSerializer(data=data)
        print(regi)
        if regi.is_valid():
            regi.save()
            return Response({"message": "successful"}, status=200)
        return Response({"message": "Course not added"}, status=500)

@api_view(["GET"])
def course_list(request):
    """this is docstring"""
    print(request.data)
    courses = Course.objects.all()
    serializer = CourseSerializer(courses, many=True)
    return Response(serializer.data)


class RegisterQuestion(APIView):
    """this is docstring"""
    def get(self,request):
        """this is docstring"""
        print(request.data)
        sub =Question.objects.all()
        serializer=QuestionSerializer(sub,many=True)
        return Response(serializer.data)
    def post(self, request):
        """this is docstring"""
        print(request.data)
        serializer= QuestionSerializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            print(serializer.data)
            return Response(serializer.data)
        print("invalid")
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

@csrf_exempt
def delete_course(request):
    """this is docstring"""
    data = JSONParser().parse(request)
    ele= Course.objects.get(id=data['id'])
    ele.delete()
    return JsonResponse("Deleted", safe=False)


@csrf_exempt
def delete_question(request):
    """this is docstring"""
    data = JSONParser().parse(request)
    print(data)
    elections = Question.objects.get(id=data['id'])
    elections.delete()
    return JsonResponse("Deleted", safe=False)

class DisplayQuestion(APIView):
    """this is docstring"""
    def get(self,request):
        """this is docstring"""
        print(request.data)
        items = []
        for i in Question.objects.all():
            items.append({
            'id':i.id,
            "question_name":i.question_name,
            "course_name":i.course.course_name
            })
        print(items)
        return Response(items)


@api_view(["GET","POST"])
def questionFilter(request):
    """this is docstring"""
    text=request.GET.get('q')
    print(text)
    data = Question.objects.filter(question_name__icontains=text)
    serializer = QuestionSerializer1(data, many=True)
    return Response(serializer.data)


class DetailQuestion(APIView):
    """this is docstring"""
    @staticmethod
    def get(request,id):
        """this is docstring"""
        print(request.data)
        questions=Question.objects.get(id=id)
        serializer=QuestionSerializer(questions, many=False)
        return Response({'question':serializer.data})

    @staticmethod
    def put(request,id):
        """this is docstring"""
        question_data =JSONParser().parse(request)
        sub=Question.objects.get(id=id)
        serializer=QuestionSerializer(sub,data=question_data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status=status.HTTP_201_CREATED, safe=False)
        return JsonResponse(serializer.errors, status=status.HTTP_400_BAD_REQUEST, safe=False)

class DetailCourse(APIView):
    """this is docstring"""
    @staticmethod
    def get(request,id):
        """this is docstring"""
        print(request.data)
        courses=Course.objects.get(id=id)
        serializer=CourseSerializer(courses, many=False)
        return Response({'course':serializer.data})

    @staticmethod
    def put(request,id):
        """this is docstring"""
        course_data =JSONParser().parse(request)
        sub=Course.objects.get(id=id)
        serializer=CourseSerializer(sub,data=course_data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status=status.HTTP_201_CREATED, safe=False)
        return JsonResponse(serializer.errors, status=status.HTTP_400_BAD_REQUEST, safe=False)


class AttemptExam(APIView):
    """this is docstring"""
    def get(self,request):
        """this is docstring"""
        print(request.data)
        data=Question.objects.all()
        serializer=QuestionSerializer2(data, many=True)
        for i in serializer.data:
            print(i['question_name'])
            for j in i:
                print(j)
        print(serializer.data[1])
        return Response(serializer.data)


class StartExam(APIView):
    """this is docstring"""
    def post(self,request):
        """this is docstring"""
        print(request.data)
        data=request.data.get('course_id')
        sub=Question.objects.filter(course=data)
        print(len(sub))
        serializer=QuestionSerializer(sub, many=True)
        print(sub)
        print(serializer.data)

        return Response(serializer.data)

class CheckMarks(APIView):
    """this is docstring"""
    def get(self,request):
        """this is docstring"""
        text=request.GET.get('q')
        print(text)
        sub=Score.objects.filter(exam_name=text)
        serializer=ScoreSerializer(sub,many=True)
        return Response(serializer.data)

    def post(self,request):
        """this is docstring"""
        print(request.data)
        text=request.GET.get('q')
        # data=request.data.get('course_name')
        sub=Score.objects.filter(exam_name=text)
        serializer=ScoreSerializer(sub,many=True)
        return Response(serializer.data)


class AttemptExam1(APIView):
    """this is docstring"""
    def get(self,request):
        """this is docstring"""
        text=request.GET.get('q')
        sub=Course.objects.filter(course_name=text)
        serializer=CourseSerializer(sub, many=True)

        for i in  serializer.data:
            k=i['id']
            print(i['id'])
            print('')
        sub1=Question.objects.filter(course=k)
        serializer1=QuestionSerializer(sub1,many=True)

        print('data+', serializer1.data)
        return Response(serializer1.data)


@api_view(["GET","POST"])
def courseFilter(request):
    """this is docstring"""
    text=request.GET.get('q')
    print(text)
    data = Course.objects.filter(course_name__icontains=text)
    serializer = CourseSerializer(data, many=True)
    return Response(serializer.data)

class Scorecard(APIView):
    """this is docstring"""
    def get(self,request):
        """this is docstring"""
        print(request.data)
        sub=Score.objects.all()
        serializer=ScoreSerializer(sub,many=True)
        return Response(serializer.data)

    def post(self,request):
        """this is docstring"""
        print(request.data)
        # score=request.data.get('score')
        # exam_name=request.data.get('exam_name')
        serializer=ScoreSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            print(serializer.data)
            return Response(serializer.data)

class AdminPanelStudent(APIView):
    """this is docstring"""
    def get(self,request):
        """this is docstring"""
        print(request.data)
        sub=Student.objects.all()
        items=[]
        students=list(sub.values('user','user__username',
        'user__first_name','user__last_name','user__email',
        'user__password','user__mobile_no','user__address',
        'reqister_number','college_name'))
        print(students)
        for i in students:
            items.append(i)
        return Response(items)

class AdminPanelTeacher(APIView):
    """this is docstring"""
    def get(self,request):
        """this is docstring"""
        print(request.data)
        sub=Teacher.objects.all()
        items=[]
        students=list(sub.values('user','user__username',
        'user__first_name','user__last_name','user__email',
        'user__password','user__mobile_no','user__address',
        'qualification','position'))
        print(students)
        for i in students:
            items.append(i)
        return Response(items)
