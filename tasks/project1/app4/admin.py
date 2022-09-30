from django.contrib import admin
from .models import Airport, Airlines, Terminal, Passenger,\
    Ticket, Schedule, Flight, StaffShifts, Luggage
# Register your models here.

admin.site.register(Airport)
admin.site.register(Terminal)
admin.site.register(Flight)
admin.site.register(Passenger)
admin.site.register(Ticket)
admin.site.register(Schedule)
admin.site.register(Airlines)
admin.site.register(StaffShifts)
admin.site.register(Luggage)




