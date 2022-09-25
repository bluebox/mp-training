from django.contrib import admin
#from .models import
from .models import Department, User, Issue, Tracking, Developers, Product, Bug, Comments

# Register your models here.
admin.site.register(Department)
admin.site.register(User)
admin.site.register(Issue)
admin.site.register(Developers)
admin.site.register(Tracking)
admin.site.register(Product)
admin.site.register(Bug)
admin.site.register(Comments)
