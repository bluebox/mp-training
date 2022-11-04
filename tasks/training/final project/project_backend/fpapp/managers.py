from fpapp.models import  Course, Question, Score, User,Student, Teacher
from rest_framework import status
from fpapp.serializer import  QuestionSerializer, QuestionSerializer1,\
    QuestionSerializer2, ScoreSerializer, UserSerializer, StudentSerializer,\
    TeacherSerializer, CourseSerializer

class CourseRegister:
    @staticmethod
    def get_course(request):
        print(request.data)
        sub =Course.objects.all()
        serializer=CourseSerializer(sub,many= True)
        return (serializer.data)

    @staticmethod
    def post_course(request):
        serializer= CourseSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            print(serializer.data)
            return (serializer.data)

    @staticmethod
    def add_course(request):
        data=request.data.get('form')
        username=request.data.get('username')
        course_name=request.data['form'].get('course_name')
        course=Course.objects.filter(course_name=course_name)
        print(data)
        print(course_name)
        if course:
            return ({"message":"course already exists"})
        user=User.objects.get(username=username)
        data['teacher_id']=user.id
        regi=CourseSerializer(data=data)
    
        if regi.is_valid():
            regi.save()
            return ({"message": "successful"})
        return ({"message": "Course not added"})

class StudentRegister:
    def get_student(request):
        print(request.data)
        sub=Student.objects.all()
        serializer=StudentSerializer(sub, many=True)
        return (serializer.data)
      
     


