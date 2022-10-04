from django.contrib import admin
from .models import GraniteStore, Vehicle, Item, Employee, Role, Customer, ContainsItem, Order, OrderedItems, Payment, Shipment
# Register your models here.

admin.site.register([GraniteStore, Vehicle, Item, Employee, Role, Customer, ContainsItem, Order, OrderedItems, Payment, Shipment],)
