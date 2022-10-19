from django.contrib import admin
from home.models import Users,Movie,Booking,Cart,Snacks,PROMOCODE,Payment,Theatre,Seating,Hall,Bill,DatewiseHall
# Register your models here.
admin.site.register(Users)
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
admin.site.register(DatewiseHall)
