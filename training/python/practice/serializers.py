from rest_framework import serializers

from .models import Departments, Professors, Courses, Student, Enrollments, Grades


class DepartmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Departments
        fields = '__all__'
class ProfessorSerializer(serializers.ModelSerializer):
    is_active = serializers.ReadOnlyField()
    class Meta:
        model = Professors
        fields = '__all__'
class CourseSerializer(serializers.ModelSerializer):
    # department = DepartmentSerializer(read_only=True)

    class Meta:
        model = Courses
        fields = '__all__'
class StudentSerializer(serializers.ModelSerializer):
    # department = DepartmentSerializer(read_only=True)
    class Meta:
        model = Student
        fields = '__all__'
class EnrollmentSerializer(serializers.ModelSerializer):
    # student=StudentSerializer(read_only=True)
    class Meta:
        model = Enrollments
        fields = '__all__'
class GradeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Grades
        fields = '__all__'
