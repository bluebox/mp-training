from django.contrib import admin

# Register your models here.
from django.contrib.auth.admin import UserAdmin
from .models import Appointment, Branch, Transaction, User, services_provided,Client

admin.site.register(User, UserAdmin)
admin.site.register(Client)
admin.site.register(Branch)
admin.site.register(Transaction)
admin.site.register(services_provided)
admin.site.register(Appointment)
