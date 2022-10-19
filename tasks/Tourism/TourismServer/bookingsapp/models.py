from django.db import models
from django.contrib.auth.base_user import AbstractBaseUser
from toursapp.models import Coupon, Tour


# Create your models here.

class User(models.Model):

    name = models.CharField(max_length=50)
    email = models.EmailField(unique=True)
    password = models.TextField()
    mobile = models.BigIntegerField()
    image = models.TextField(
    default='https://res.cloudinary.com/dklq1vuce/image/upload/v1664469247/smiley-dp_nu8kdg.jpg'
    )
    isAdmin = models.BooleanField(default=False)
    is_active = models.BooleanField(default=True)
    created_at = models.DateTimeField(auto_now_add=True)
    last_logged_at = models.DateTimeField(auto_now=True)

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = []
    def __str__(self):
        return self.email + ''

    class Meta:
        db_table = 'users'


class UserToken(models.Model):

    user_id = models.IntegerField()
    token = models.CharField(max_length=255)
    expired_at = models.DateTimeField()
    created_at = models.DateTimeField(auto_now_add=True)


class PaymentDetails(models.Model):

    payment_type = models.CharField(max_length=50)
    transaction_id = models.CharField(max_length=50, unique=True)
    total_price = models.FloatField(null=True)
    coupon_applied = models.ForeignKey(Coupon, on_delete=models.CASCADE, null=True, blank=True)
    created_at = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.transaction_id + ''

    class Meta:
        db_table = 'payment_details'


class BookingDetails(models.Model):

    userid = models.ForeignKey(User, on_delete=models.CASCADE)
    tourid = models.ForeignKey(Tour, on_delete=models.CASCADE)
    paymentid = models.ForeignKey(PaymentDetails, on_delete=models.CASCADE)
    no_of_people = models.IntegerField()
    passenger_details = models.TextField()
    isCancelled = models.BooleanField(default=False)
    created_at = models.DateTimeField(auto_now_add=True)


    class Meta:
        db_table = 'booking_details'
        # order_by = ['created_at']


class Feedback(models.Model):

    user_id = models.OneToOneField(User, on_delete=models.CASCADE, null=True)
    comment = models.TextField(null=True)
    rating = models.FloatField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)


    class Meta:
        db_table = 'feedback'


class CancellationDetails(models.Model):

    bookingid = models.OneToOneField(BookingDetails, on_delete=models.CASCADE)
    refund_status = models.TextField()
    cancellation_charges = models.FloatField()
    reason_for_cancellation = models.TextField(blank=True, null=True)
    created_at = models.DateTimeField(auto_now_add=True)


    class Meta:
        db_table = 'cancellation_details'
