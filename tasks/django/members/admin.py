from django.contrib import admin
   
# Register your models here.
from .models import StudentInfo, Subjects, Marks, Branch
   
admin.site.register(StudentInfo)
admin.site.register(Subjects)
admin.site.register(Marks)
admin.site.register(Branch)