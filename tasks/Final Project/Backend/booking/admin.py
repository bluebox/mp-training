from django.contrib import admin
from .models import Booking,BookingItem, BookingAddress
# Register your models here.

admin.site.register(Booking)
admin.site.register(BookingItem)
admin.site.register(BookingAddress)

