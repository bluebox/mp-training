from django.contrib import admin
from home.models import User,Movie,Bill,Booking,Cart,Snacks,PROMOCODE,Payment,Theatre,Seating,Hall
# Register your models here.
admin.site.register(User)
admin.site.register(Hall)
admin.site.register(Bill)
admin.site.register(Cart)
admin.site.register(Movie)
admin.site.register(Booking)
admin.site.register(Snacks)
admin.site.register(PROMOCODE)
admin.site.register(Payment)
admin.site.register(Theatre)
# admin.site.register(Selected_seats)
admin.site.register(Seating)
