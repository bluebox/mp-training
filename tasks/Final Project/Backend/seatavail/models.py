from django.db import models
from cinema.models import Cinema_Seat
from show.models import Show
from booking.models import Booking
# Create your models here.

class ShowSeat(models.Model):
    show_seat_id = models.AutoField(primary_key=True, editable=False)
    status = models.IntegerField(null=True, blank=True, default=1)
    price = models.IntegerField(null=True, blank=True, default=1)
    cinema_seat = models.ForeignKey(Cinema_Seat , on_delete=models.SET_NULL, null=True)
    show = models.ForeignKey(Show , on_delete=models.SET_NULL, null=True)
    booking = models.ForeignKey(Booking , on_delete=models.SET_NULL, null=True)
