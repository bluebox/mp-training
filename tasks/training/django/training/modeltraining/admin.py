from django.contrib import admin
from django.contrib.auth.admin import UserAdmin
from .models import  Address,Role,Profile
# ,UpdatedUser
# Register your models here.

# admin.site.register(UpdatedUser,UserAdmin)

admin.site.register(Address)
admin.site.register(Role)

admin.site.register(Profile)



