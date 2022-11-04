from django.contrib import admin

from .models import Order, OrderedItems, DeliveryT

# Register your models here.
admin.site.register([Order, OrderedItems, DeliveryT])
