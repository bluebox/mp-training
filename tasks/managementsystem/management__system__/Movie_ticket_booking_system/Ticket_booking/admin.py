from django.contrib import admin
   
# Register your models here.
from .models import Admin,Customer,Movie,Hall,Reservation,Transaction,Ticket,Seating_arrangement,Report
   
admin.site.register(Admin)
admin.site.register(Customer)
admin.site.register(Movie)
admin.site.register(Reservation)
admin.site.register(Transaction)
admin.site.register(Ticket)
admin.site.register(Seating_arrangement)
admin.site.register(Report)

