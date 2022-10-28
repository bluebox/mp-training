"""admin file"""
from django.contrib import admin
from django.contrib.auth.admin import UserAdmin
from .models import Score, Student, Teacher, Course, Question

from .models import User


class CustomUserAdmin(UserAdmin):
    """this is class"""

    fieldsets =(
        *UserAdmin.fieldsets,
        (
            'Additional Info',
            {
                'fields' : (
                    'user_type','mobile_no','address'
                )
            }
        )
    )

admin.site.register(User,CustomUserAdmin)

admin.site.register(Student)
admin.site.register(Teacher)
admin.site.register(Score)
admin.site.register(Course)
admin.site.register(Question)
