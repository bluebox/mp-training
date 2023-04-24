from django.contrib import admin
from . models import *

admin.site.register(Address)
admin.site.register(Education)
admin.site.register(Experience)
admin.site.register(Skill)
admin.site.register(SkillDetails)
admin.site.register(Company)
admin.site.register(JobPost)
admin.site.register(JobApplications)

