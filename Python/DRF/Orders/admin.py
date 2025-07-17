from django.contrib import admin

from .models import Customer, Order, Product, OrderItem

admin.site.register(Customer)
admin.site.register(Order)
admin.site.register(Product)
admin.site.register(OrderItem)