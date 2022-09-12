from django.contrib import admin

# Register your models here.
from .models import *

admin.site.register(Customer)
admin.site.register(Manufacturer)
admin.site.register(Employee)
admin.site.register(Doctor)
admin.site.register(Distributor)
admin.site.register(Drug)
admin.site.register(Sales)
admin.site.register(Prescribe)
admin.site.register(Purchase)
admin.site.register(Supply)
admin.site.register(Order)
admin.site.register(Cart)
admin.site.register(Invoice)

