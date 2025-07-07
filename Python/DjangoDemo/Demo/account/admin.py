from django.contrib import admin
from .models import Student, Example, Employee

# Register your models here.
admin.site.register(Student)
admin.site.register(Example)
admin.site.register(Employee)