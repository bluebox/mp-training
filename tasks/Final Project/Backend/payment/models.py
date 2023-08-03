from django.db import models
from booking.models import Booking
# Create your models here.

class Payment(models.Model):
    payment_id = models.AutoField(primary_key=True, editable=False)
    amount = models.DecimalField(max_digits=7, decimal_places=2, null= True, blank=True)
    time_stamp = models.DateTimeField(auto_now_add=False, null=True, blank=True)
    # discountCouponId = 
    # remoteTransactionId = 
    payment_method = models.CharField(max_length=50, null=True, blank=True)
    booking = models.ForeignKey(Booking, on_delete=models.SET_NULL, null=True)
