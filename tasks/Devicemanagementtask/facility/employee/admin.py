from django.contrib import admin
from .models import *
# Register your models here.
admin.site.register(Facility)
admin.site.register(Employee)
admin.site.register(Role)
admin.site.register(Device)
admin.site.register(Employee_Devices)
admin.site.register(Complaint)
admin.site.register(Issued_to)


