from django.contrib import admin
from model_training.models import Customer,Cart,Invoice,Payment,Order,Menu
# Register your models here.
admin.site.register(Customer)
admin.site.register(Cart)
admin.site.register(Invoice)
admin.site.register(Payment)
admin.site.register(Order)
admin.site.register(Menu)