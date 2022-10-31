"""admin"""
from django.contrib import admin

# Register your models here.
from django.contrib.auth.admin import UserAdmin
from .models import Appointment, Branch, Employee, Transaction, User, ServicesProvided,\
    Client,Reviews

admin.site.register(User, UserAdmin)
admin.site.register(Client)
admin.site.register(Branch)
admin.site.register(Employee)
admin.site.register(Transaction)
admin.site.register(ServicesProvided)
admin.site.register(Appointment)
admin.site.register(Reviews)
