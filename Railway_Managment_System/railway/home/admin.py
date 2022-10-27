from django.contrib import admin
from home.models import User,Coach,Train,Ticket,Station,Booking,Book,Passenger,Cancel,TrainStatus
# Register your model
admin.site.register(User)
admin.site.register(Coach)
admin.site.register(Train)
admin.site.register(Ticket)
admin.site.register(Station)
admin.site.register(Booking)
admin.site.register(Passenger)
admin.site.register(Book)
admin.site.register(Cancel)
admin.site.register(TrainStatus)

