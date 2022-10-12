
from rest_framework import serializers
from fpapp.models import  Course, Question, User, Student, Teacher


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['username','first_name','last_name','email','password','mobile_no','address','user_type']

    def create(self, validated_data):
        return User.objects.create_user(**validated_data)

    
class StudentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Student
        fields = '__all__'


class TeacherSerializer(serializers.ModelSerializer):
    class Meta:
        model = Teacher
        fields = '__all__'

# class SubjectSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = Subject
#         fields ='__all__'

class CourseSerializer(serializers.ModelSerializer):
    
    class Meta:
        model = Course
        fields = '__all__'

class QuestionSerializer(serializers.ModelSerializer):

    class Meta:
        model=Question
        fields='__all__'

class QuestionSerializer1(serializers.ModelSerializer):
    course_name= serializers.CharField(source='course.course_name')
    class Meta:
        model=Question
        fields=('question_name','course_name')

class QuestionSerializer2(serializers.ModelSerializer):
    c_name= serializers.CharField(source='course.course_name')
    class Meta:
        model=Question
        fields=('question_name','option1','option2','option3','option4','c_name','course')

