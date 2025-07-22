from rest_framework import serializers
from .models import *

class StudentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Student
        fields = '__all__'

class TeacherSerializer(serializers.ModelSerializer):
    class Meta:
        model = Teacher
        fields = '__all__'

class SubjectSerializer(serializers.ModelSerializer):
    class Meta:
        model = Subject
        fields = '__all__'

class ResultsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Results
        fields = '__all__'

class ClassesSerializer(serializers.ModelSerializer):
    teacher = TeacherSerializer(read_only=True)
    class Meta:
        model = Classes
        fields = '__all__'

class SubjectClassTeacherSerializer(serializers.ModelSerializer):
    class Meta:
        model = subject_teacher
        fields = '__all__'

class StudentProfileSerializer(serializers.ModelSerializer):
    class Meta:
        model = StudentProfile
        fields = '__all__'

class StudentResultsDashboard(serializers.ModelSerializer):
    results_set = ResultsSerializer(many=True, read_only=True)
    class Meta:
        model = Student
        fields = '__all__'

class StudentDetailsSerializer(serializers.ModelSerializer):
    studentprofile = StudentProfileSerializer(read_only=True)
    Class = ClassesSerializer(read_only=True)
    class Meta:
        model = Student
        fields = '__all__'


