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
      
     


