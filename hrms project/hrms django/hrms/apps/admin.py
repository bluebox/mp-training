from django.contrib import admin
from . models import  *
from django.contrib.sessions.models import Session

# Register your models here.
admin.site.register(log)
admin.site.register(role)
admin.site.register(leave)
admin.site.register(salary)
admin.site.register(insurance)
admin.site.register(Attendence)
admin.site.register(Team_lead)
admin.site.register(department)
admin.site.register(personal_details)
admin.site.register(Session)

