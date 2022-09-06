from django.contrib import admin
from django.contrib.auth.admin import UserAdmin
from .models import  Address, Bill,Role,Profile , Room ,Patient,InPatient,OutPatient
# ,UpdatedUser
# Register your models here.

# admin.site.register(UpdatedUser,UserAdmin)

admin.site.register(Address)
admin.site.register(Role)
admin.site.register(Profile)
admin.site.register(Room)
admin.site.register(Patient)
admin.site.register(InPatient)
admin.site.register(OutPatient)
admin.site.register(Bill)





