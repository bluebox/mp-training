from django.contrib import admin
from  . import models
# Register your models here.
admin.site.register(models.Branch)
admin.site.register(models.Appointment)
admin.site.register(models.Bill)
admin.site.register(models.Lab)
admin.site.register(models.Report)
admin.site.register(models.Review)
admin.site.register(models.Test)