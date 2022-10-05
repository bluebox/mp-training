from django.contrib import admin
from .models import Student, Teacher, Course, Question, Evaluation
from django.contrib.auth.admin import UserAdmin
from .models import User


class CustomUserAdmin(UserAdmin):
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
admin.site.register(Evaluation)
# admin.site.register(ExamAttemptDetail)
admin.site.register(Course)
admin.site.register(Question)