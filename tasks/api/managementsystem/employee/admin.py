from django.contrib import admin

# Register your models here.
from .models import *
admin.site.register(Facility)
admin.site.register(Role)
admin.site.register(Device)
admin.site.register(Employee_Devices)
admin.site.register(Complaint)
admin.site.register(Issued_to)
admin.site.register(Employee)