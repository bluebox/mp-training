from django.contrib import admin
from .models import Patients, Appointment, Doctor, Staff, Salary, Test, Diagnosis, Bills
# Register your models here.
admin.site.register(Patients)
admin.site.register(Appointment)
admin.site.register(Doctor)
admin.site.register(Staff)
admin.site.register(Salary)
admin.site.register(Test)
admin.site.register(Diagnosis)
admin.site.register(Bills)

