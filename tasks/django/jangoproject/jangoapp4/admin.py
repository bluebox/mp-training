from django.contrib import admin
from jangoapp4.models import PracticeModel,Student,Professor,department,college,Location
# Register your models here.
admin.site.register(PracticeModel)
admin.site.register(Student)
admin.site.register(Location)
admin.site.register(college)
admin.site.register(department)
admin.site.register(Professor)