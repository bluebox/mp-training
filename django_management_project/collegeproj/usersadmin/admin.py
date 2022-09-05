from re import sub
from django.contrib import admin
from .models import adminusers,students,courses,subject,faculties,assign_subject,enter_marks
# Register your models here.
admin.site.register(adminusers)
admin.site.register(students)
admin.site.register(courses)
admin.site.register(subject)
admin.site.register(faculties)
admin.site.register(assign_subject)
admin.site.register(enter_marks)