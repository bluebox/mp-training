from rest_framework import serializers
from .models import faculty_details

# class FacultySerializer(serializers.ModelSerializer):
#     class Meta:
#         model= faculty_details
#         fields =(
#             'first_name',
#             'last_name',
#             'facul_username',
#             'password',
#             'gender',
#             'date_of_birth',
#             'main_subject',
#             'department',
#             'joining_date',
#             'qualification',
#             'experience',
#             'experience',
#             'user_type'
#         )
#         def create(self, validated_data):
#             faculty = faculty_details.objects.create_user(**validated_data)
#             return faculty
