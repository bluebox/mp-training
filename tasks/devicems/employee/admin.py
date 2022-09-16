from django.contrib import admin
from .models import *
# Register your models here.
admin.site.register(Employee)
admin.site.register(Manager)
admin.site.register(Device)
admin.site.register(Employee_Devices)
admin.site.register(complaint)
admin.site.register(Issued_to)
admin.site.register(Department)
