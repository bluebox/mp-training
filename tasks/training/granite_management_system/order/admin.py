from django.contrib import admin

from .models import Order, OrderedItems, itemsDelivery

# Register your models here.
admin.site.register([Order, OrderedItems, itemsDelivery])
