"""this is docstring"""
#pylint:disable=R0903
from rest_framework import serializers
from fpapp.models import  Course, Question, Score, User, Student, Teacher

class UserSerializer(serializers.ModelSerializer):
    """this is docstring"""
    class Meta:
        """this is docstring"""
        model = User
        fields = ['username','first_name','last_name','email','password','mobile_no',
        'address','user_type']

    def create(self, validated_data):
        """this is docstring"""
        return User.objects.create_user(**validated_data)

class StudentSerializer(serializers.ModelSerializer):
    """this is docstring"""
    class Meta:
        """this is docstring"""
        model = Student
        fields = '__all__'

class TeacherSerializer(serializers.ModelSerializer):
    """this is docstring"""
    class Meta:
        """this is docstring"""
        model = Teacher
        fields = '__all__'

class CourseSerializer(serializers.ModelSerializer):
    """this is docstring"""
    class Meta:
        """this is docstring"""
        model = Course
        fields = '__all__'

class QuestionSerializer(serializers.ModelSerializer):
    """this is docstring"""
    class Meta:
        """this is docstring"""
        model=Question
        fields='__all__'

class QuestionSerializer1(serializers.ModelSerializer):
    """this is docstring"""
    course_name= serializers.CharField(source='course.course_name')
    class Meta:
        """this is docstring"""
        model=Question
        fields=('question_name','course_name')

class QuestionSerializer2(serializers.ModelSerializer):
    """this is docstring"""
    c_name= serializers.CharField(source='course.course_name')
    class Meta:
        """this is docstring"""
        model=Question
        fields=('question_name','option1','option2','option3','option4','c_name','course')


# class EvaluationSerializer(serializers.ModelSerializer):
#     class Meta:
#         model=Evaluation
#         fields='__all__'

class ScoreSerializer(serializers.ModelSerializer):
    """this is docstring"""
    class Meta:
        """this is docstring"""
        model=Score
        fields='__all__'
