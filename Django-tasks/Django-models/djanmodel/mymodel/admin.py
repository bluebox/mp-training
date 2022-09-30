from django.contrib import admin

# Register your models here.
from .models import User, Ticket, Train, TrainStatus, Station, Started, Stopped, Reach, Passenger, Book, Cancel

admin.site.register(User)
admin.site.register(Train)
admin.site.register(Ticket)
admin.site.register(TrainStatus)
admin.site.register(Station)
admin.site.register(Started)
admin.site.register(Stopped)
admin.site.register(Reach)
admin.site.register(Passenger)
admin.site.register(Book)
admin.site.register(Cancel)


