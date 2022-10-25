from rest_framework import serializers
from Admin.models import faculty_details
from Admin.models import subject_details
from Admin.models import department_details


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
            'user_type'
        )


class SubjectSerializer(serializers.ModelSerializer):
    class Meta:
        model = subject_details
        fields = (
            'subject_code',
            'subject_name',
            'category'
        )


class DepartmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = department_details
        fields = (
            'department_code',
            'department_name'
        )