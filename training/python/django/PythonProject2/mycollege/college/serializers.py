from rest_framework import serializers
from .models import Student, Department, StudentProfile, Teacher, Course, Enrollment, TeacherCourse


class StudentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Student
        fields = ['student_id', 'first_name', 'last_name', 'email', 'dept']
        # fields = '__all__'

class DepartmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Department
        fields = ['dept_id', 'dept_name']


class StudentProfileSerializer(serializers.ModelSerializer):
    class Meta:
        model = StudentProfile
        # fields = ['student', 'dob', 'address', 'phone']
        fields = '__all__'


class StudentFullProfileSerializer(serializers.ModelSerializer):
    student = StudentSerializer(read_only=True)

    class Meta:
        model = StudentProfile
        fields = ['student', 'dob', 'address', 'phone']


class TeacherSerializer(serializers.ModelSerializer):
    class Meta:
        model = Teacher
        fields = ['teacher_id', 'first_name', 'last_name', 'email', 'dept']
        # fields = '__all__'


class CourseSerializer(serializers.ModelSerializer):
    class Meta:
        model = Course
        fields = ['course_id', 'course_name', 'credits', 'dept']
        # fields = '__all__'


class EnrollmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Enrollment
        fields = ['enrollment_id', 'student', 'course', 'internal_marks', 'external_marks']
        # fields = '__all__'


class TeacherCourseSerializer(serializers.ModelSerializer):
    class Meta:
        model = TeacherCourse
        fields = ['teacher', 'course']
        # fields = '__all__'
