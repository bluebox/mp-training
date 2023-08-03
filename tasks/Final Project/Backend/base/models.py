from django.db import models
from django.contrib.auth.models import User

# Create your models here.

class Movie(models.Model):
    user = models.ForeignKey(User, on_delete=models.SET_NULL, null=True)
    name = models.CharField(max_length=200, null=True, blank=True)
    image = models.ImageField(null=True, blank=True,
             default='/placeholder.png')
    productionHouse = models.CharField(max_length=200, null=True, blank=True)
    category = models.CharField(max_length=200, null=True, blank=True)
    description = models.TextField()
    rating = models.DecimalField(max_digits=7, decimal_places=2, null=True, blank=True)
    numReviews = models.IntegerField(null=True, blank=True, default=0)
    price = models.DecimalField(max_digits=7, decimal_places=2, null= True, blank=True) 
    number_of_screens = models.IntegerField(null=True, blank=True, default=0)
    createdAt = models.DateTimeField(auto_now_add=True)
    _id = models.AutoField(primary_key=True, editable=False)
    def __str__(self):
        return self.name
    
# class Review(models.Model):
#     movie = models.ForeignKey(Movie, on_delete=models.SET_NULL, null=True)
#     user = models.ForeignKey(User, on_delete=models.SET_NULL, null=True)
#     name = models.CharField(max_length=200, null=True, blank=True)
#     rating = models.IntegerField(null=True, blank=True, default=0)
#     comment = models.TextField(null= True, blank=True)
#     _id = models.AutoField(primary_key=True, editable=False)

#     def __str__(self):
#         return str(self.rating)

# class OrderDetail(models.Model):
#     user = models.ForeignKey(User, on_delete=models.SET_NULL, null=True)
#     paymentMethod = models.CharField(max_length=200, null=True, blank=True)
#     taxPrice = models.DecimalField(max_digits=7, decimal_places=2, null= True, blank=True)
#     convenienceCharge = models.DecimalField(max_digits=7, decimal_places=2, null= True, blank=True)
#     totalPrice = models.DecimalField(max_digits=7, decimal_places=2, null= True, blank=True)
#     isPaid = models.BooleanField(default=False)
#     paidAt = models.DateTimeField(auto_now_add=False, null=True, blank=True)
#     isTicket = models.BooleanField()
#     createdAt = models.DateTimeField(auto_now_add=True)
#     _id = models.AutoField(primary_key=True, editable=False)

#     def __str__(self):
#         return str(self.createAt)

# class OrderItem(models.Model):
#     movie = models.ForeignKey(Movie, on_delete=models.SET_NULL, null=True)
#     order_detail = models.ForeignKey(OrderDetail, on_delete=models.SET_NULL, null=True)
#     name = models.CharField(max_length=200, null=True, blank=True)
#     number_of_tickets = models.IntegerField(null=True, blank=True, default=1)
#     price = models.DecimalField(max_digits=7, decimal_places=2, null= True, blank=True)
#     # image_ticket = 
#     _id = models.AutoField(primary_key=True, editable=False)

#     def __str__(self):
#         return str(self.name)

# class CinemaHallAddress(models.Model):
#     order = models.OneToOneField(OrderDetail, on_delete=models.CASCADE, null=True, blank=True)
#     address = models.CharField(max_length=200, null=True, blank=True)
#     city = models.CharField(max_length=200, null=True, blank=True)
#     postalCode = models.CharField(max_length=200, null=True, blank=True)
#     country = models.CharField(max_length=200, null=True, blank=True)
#     _id = models.AutoField(primary_key=True, editable=False)

#     def __str__(self):
#         return str(self.name)

    

    









# class User(models.Model):
#     id = models.Foreign()
#     username = models.CharField(max_length=200)
#     first_name = models.CharField(max_length=50)
#     last_name = models.CharField(max_length=50)
#     email = models.CharField(max_length=50)
#     password = models.CharField(max_length=50)
#     is_staff = models.BooleanField()
#     is_active = models.BooleanField()
#     is_superuser = models.BooleanField()

#     def __str__(self):
#         return self.username
