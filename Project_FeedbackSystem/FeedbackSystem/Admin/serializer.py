from rest_framework import serializers
from .models import faculty_details, student_details

class FacultySerializer(serializers.ModelSerializer):
    class Meta:
        model= faculty_details
        fields =(
            'first_name',
            'last_name',
            'facul_username',
            'password',
            'gender',
            'date_of_birth',
            'main_subject',
            'department',
            'joining_date',
            'qualification',
            'experience',
            'user_type',
            'experience',
            'user_type'
        )

class StudentSerializer(serializers.ModelSerializer):
    class Meta:
        model = student_details
        fields = (
            'first_name',
            'last_name',
            'stud_username',
            'password',
            'gender',
            'date_of_birth',
            'father_name',
            'roll_no',
            'class_name',
            'user_type'
        )
