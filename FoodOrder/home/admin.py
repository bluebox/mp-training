from django.contrib import admin

# Register your models here.

from .models import Restaurant, Customer, Employee, Food, OrderFood, Payment, Menu, OrderDetails, Reviews

admin.site.register(Restaurant)

admin.site.register(Customer)

admin.site.register(Employee)

admin.site.register(Food)

admin.site.register(OrderFood)

admin.site.register(Payment)

admin.site.register(Menu)

admin.site.register(OrderDetails)

admin.site.register(Reviews)