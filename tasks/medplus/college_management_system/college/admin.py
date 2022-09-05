from django.contrib import admin
from .models import *
# Register your models here.
admin.site.register(professor)
admin.site.register(students)
admin.site.register(student_details)
admin.site.register(students_attendence)
admin.site.register(professor_attendance)
admin.site.register(schedule)
admin.site.register(course)
admin.site.register(department)
