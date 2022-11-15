from django.contrib import admin

from .models import Item, ContainsItem, GraniteStore, Cart

# Register your models here.
admin.site.register([Item, ContainsItem, GraniteStore, Cart])
