from django.contrib import admin
from .models import Student_Info, Subjects, Marks

admin.site.register(Student_Info)
admin.site.register(Marks)
admin.site.register(Subjects)
