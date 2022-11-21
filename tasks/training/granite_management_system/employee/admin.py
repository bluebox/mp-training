from django.contrib import admin

from .models import Employee, Role

# Register your models here.
admin.site.register([Employee, Role])
