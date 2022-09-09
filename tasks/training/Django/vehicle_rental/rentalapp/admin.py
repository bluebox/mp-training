from django.contrib import admin
from .models import *
# Register your models here.
admin.site.register(Owner)
admin.site.register(Customer)
admin.site.register(Vehicle)
admin.site.register(Vehicle_status)
admin.site.register(Rent_Trip)
admin.site.register(Bill)