from django.db import models
from show.models import Show
from django.contrib.auth.models import User
from base.models import Movie

# Create your models here.
# class Booking1(models.Model):
#     booking_id = models.AutoField(primary_key=True, editable=False)
#     number_of_seats = models.IntegerField()
#     time_stamp = models.DateTimeField
#     status = models.BooleanField() 
#     user = models.ForeignKey(User, on_delete=models.SET_NULL, null=True)
#     show = models.ForeignKey(Show , on_delete=models.SET_NULL, null=True)

class Booking(models.Model):
    user = models.ForeignKey(User, on_delete=models.SET_NULL, null=True)
    paymentMethod = models.CharField(max_length=200, null=True, blank=True)
    taxPrice = models.DecimalField(max_digits=7, decimal_places=2, null= True, blank=True)
    convenienceCharge = models.DecimalField(max_digits=7, decimal_places=2, null= True, blank=True)
    totalPrice = models.DecimalField(max_digits=7, decimal_places=2, null= True, blank=True)
    isPaid = models.BooleanField(default=False)
    paidAt = models.DateTimeField(auto_now_add=False, null=True, blank=True)
    isTicket = models.BooleanField(null = True, blank=True)
    createdAt = models.DateTimeField(auto_now_add=True, null= True, blank=True)
    booking_id = models.AutoField(primary_key=True, editable=False)

    def __str__(self):
        return str(self.createdAt)

class BookingItem(models.Model):
    movie = models.ForeignKey(Movie, on_delete=models.SET_NULL, null=True)
    order_detail = models.ForeignKey(Booking, on_delete=models.SET_NULL, null=True, related_name='bookingItem')
    name = models.CharField(max_length=200, null=True, blank=True)
    number_of_tickets = models.IntegerField(null=True, blank=True, default=1)
    price = models.DecimalField(max_digits=7, decimal_places=2, null= True, blank=True)
    # image_ticket = models.CharField(max_length=200, null=True, blank=True)
    _id = models.AutoField(primary_key=True, editable=False)

    def __str__(self):
        return str(self.name)
    
class BookingAddress(models.Model):
    order = models.OneToOneField(Booking, on_delete=models.CASCADE, null=True, blank=True)
    address = models.CharField(max_length=200, null=True, blank=True)
    city = models.CharField(max_length=200, null=True, blank=True)
    postalCode = models.CharField(max_length=200, null=True, blank=True)
    country = models.CharField(max_length=200, null=True, blank=True)
    bookingPrice = models.DecimalField(max_digits=7, decimal_places=2, null=True, blank=True)
    _id = models.AutoField(primary_key=True, editable=False)

    def __str__(self):
        return str(self.city)
