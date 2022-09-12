from statistics import mode
from django.contrib import admin
from django.contrib.auth.admin import UserAdmin
from . import models

class CustomUserAdmin(UserAdmin):
    fieldsets =(
        *UserAdmin.fieldsets,
        (
            'Additional Info',
            {
                'fields' : (
                    'mobile_number','age','addres'
                )
            }
        )
    )

admin.site.register(models.User,CustomUserAdmin)

admin.site.register(models.Roles)
admin.site.register(models.User_role)
admin.site.register(models.Lab)
admin.site.register(models.Test)
admin.site.register(models.Review)
admin.site.register(models.Appointment)

admin.site.register(models.Report)
admin.site.register(models.Bill)





