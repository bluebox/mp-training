from rest_framework import serializers
from Admin.models import student_details
from Admin.models import class_details


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
            'class_code',
            'user_type',
            'status'
        )

class ClassSerializer(serializers.ModelSerializer):
    class Meta:
        model = class_details
        fields = (
            'class_name',
            'class_code'
        )
    # def create(self, validated_data):
    #     student = self.model.objects.create_user(**validated_data)
    #     print(student)
    #     return student
