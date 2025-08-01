from rest_framework import serializers
from .models import *



class TeacherSerializer(serializers.ModelSerializer):
    class Meta:
        model = Teacher
        fields = '__all__'
        read_only_fields = ['user']
    def validate_Name(self, value):
        if len(value) <3:
            raise serializers.ValidationError("Name must be atleast 3 characters long")
        return value
    def validate_experience(self,value):
        if value<0:
            raise serializers.ValidationError("Experience must be positive number")
        return value

    def create(self, validated_data):
        user = BaseUser.objects.create_user(
            username=f"user_{BaseUser.objects.count() + 1}",
            password="password123",
            role=BaseUser.Role.TEACHER
        )
        validated_data['user'] = user
        return Teacher.objects.create(**validated_data)


class SubjectSerializer(serializers.ModelSerializer):
    class Meta:
        model = Subject
        fields = '__all__'


class ResultsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Results
        fields = '__all__'

class ClassesSerializer(serializers.ModelSerializer):
    # teacher = TeacherSerializer(read_only=True)
    class Meta:
        model = Classes
        fields = '__all__'

class StudentSerializer(serializers.ModelSerializer):
    # Class = ClassesSerializer(read_only=True)
    # Class_id = serializers.PrimaryKeyRelatedField(
    #     queryset=Classes.objects.all(), write_only=True, source='Class'
    # )
    class Meta:
        model = Student
        fields = [
            'Name', 'Class', 'Class_id', 'attendance',
            'is_class_representative', 'status', 'user'
        ]
        read_only_fields = ['user']
    def create(self, validated_data):

        user = BaseUser.objects.create_user(
            username=f"user_{BaseUser.objects.count()+1}",
            password="password123",
            role=BaseUser.Role.STUDENT
        )
        validated_data['user'] = user
        return Student.objects.create(**validated_data)
    def validate_Name(self,value):
        if len(value)<3:
            raise serializers.ValidationError("Name must be atleast 3 characters long")
        return value
    def validate_attendance(self,value):
        if value<0:
            raise serializers.ValidationError("attendance must be a positive number")
        return value
    # def validate_(self, value):


class SubjectClassTeacherSerializer(serializers.ModelSerializer):
    class Meta:
        model = subject_teacher
        fields = '__all__'

class StudentProfileSerializer(serializers.ModelSerializer):
    class Meta:
        model = StudentProfile
        fields = '__all__'
    def validate_FatherName(self,value):
        if len(value)<3:
            raise serializers.ValidationError("Father Name must be atleast 3 characters long")
        return value
    def validate_MotherName(self,value):
        if len(value)<3:
            raise serializers.ValidationError("Mother Name must be atleast 3 characters long")
        return value
    def validate_FatherAge(self,value):
        if value<0:
            raise serializers.ValidationError("Fathers age must be a positive number")
        return value
    def validate_MotherAge(self,value):
        if value<0:
            raise serializers.ValidationError("Mothers age must be a positive number")
        return value

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
#
# class StudentProfileSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = StudentProfile
#         fields = [
#             "Age",
#             "FatherName",
#             "MotherName",
#             "FatherAge",
#             "MotherAge",
#             "address",
#             "phoneNo"
#         ]

class TeacherSubjectSerializer(serializers.ModelSerializer):
    class Meta:
        model = subject_teacher
        fields = "__all__"


