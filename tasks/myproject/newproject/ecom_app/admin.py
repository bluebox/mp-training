from django.contrib import admin
from ecom_app import models
from ecom_app.models import *

# Register your models here.
# admin.site.register(Customer)
# admin.site.register(Product_list)
admin.site.register(Order_list)
admin.site.register(cust_cart_list)
admin.site.register(Total_delivered_items)
admin.site.register(return_list)
admin.site.register(Cust_delivered_items)
admin.site.register(delivery_person)
# admin.site.register(Employees)
admin.site.register(pickup)
admin.site.register(Products_Details)
admin.site.register(Product_Type)
admin.site.register(cart_details)

