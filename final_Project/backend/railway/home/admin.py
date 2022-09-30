from django.contrib import admin
from home.models import Schedule,Pnr, Seat_Chart, User,Coach,Train,Ticket,Station,Book,Passenger,Cancel,TrainStatus,StartDateOfJourney,EndDateOfJourney
# Register your model
admin.site.register(User)
admin.site.register(Coach)
admin.site.register(Train)
admin.site.register(Pnr)
admin.site.register(Ticket)
admin.site.register(Station)
admin.site.register(Schedule)
admin.site.register(Seat_Chart)
admin.site.register(Passenger)
admin.site.register(Book)
admin.site.register(Cancel)
admin.site.register(TrainStatus)
admin.site.register(StartDateOfJourney)
admin.site.register(EndDateOfJourney)


