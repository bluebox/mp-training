
from rest_framework import serializers
from fpapp.models import  Course, User, Student, Teacher


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['username','first_name','last_name','email','password','mobile_no','address']

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
