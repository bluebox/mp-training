from django.contrib import admin

# Register your models here.
from django.contrib.auth.admin import UserAdmin
from .models import Branch, User, services_provided

admin.site.register(User, UserAdmin)
admin.site.register(Branch)
admin.site.register(services_provided)