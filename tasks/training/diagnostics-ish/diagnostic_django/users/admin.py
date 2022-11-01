"""admin site file"""
from django.contrib.auth.admin import UserAdmin
from django.contrib import admin
from . import models

# Register your models here.

class CustomUserAdmin(UserAdmin):
    """customizing user admin to add additional fields for user"""
    fieldsets = (
        *UserAdmin.fieldsets,
        (
            'Additional Info',
            {
                'fields' : (
                    'mobile_number', 'age', 'address', 'pincode', 'user_type'
                )
            }
        )
    )

admin.site.register(models.Customer)
admin.site.register(models.Staff)
admin.site.register(models.User, CustomUserAdmin)
