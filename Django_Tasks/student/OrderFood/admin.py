from django.contrib import admin

# Register your models here.
from OrderFood.models import Restaurant, Customer, Employee, Food, OrderFood, Payment, FoodAvailable, OrderDetails

admin.site.register(Restaurant)

admin.site.register(Customer)

admin.site.register(Employee)

admin.site.register(Food)

admin.site.register(OrderFood)

admin.site.register(Payment)

admin.site.register(FoodAvailable)

admin.site.register(OrderDetails)