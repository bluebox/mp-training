from django.contrib import admin
from users.models import AllUser, Appointment, Doctor, Patients, Staff,DatewiseSlot

# Register your models here.
admin.site.register(Patients)
admin.site.register(AllUser)
admin.site.register(Doctor)
admin.site.register(Staff)
admin.site.register(DatewiseSlot)
admin.site.register(Appointment)

