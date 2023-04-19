from django.db import models
from django.contrib.auth.models import User
from base.models import Product

class ShippingAddress(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    locality = models.CharField(max_length=255, null=True, blank=True)
    city =  models.CharField(max_length=255, null=True, blank=True)
    state =  models.CharField(max_length=255, null=True, blank=True)
    postalCode = models.CharField(max_length=255, null=True, blank=True)

class Order(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    paymentMethod = models.CharField(max_length=255, null=True, blank=True)
    taxPrice = models.DecimalField(max_digits=7, decimal_places=2, null=True, blank=True)
    totalPrice = models.DecimalField(max_digits=7, decimal_places=2, null=True, blank=True)
    shippingPrice = models.DecimalField(max_digits=7, decimal_places=2, default=0.00, blank=True, null=True)
    isPaid = models.BooleanField(default=False )
    paidAt = models.DateTimeField(auto_now_add=False, null=True, blank=True)
    isDelivered = models.BooleanField(default=False )
    deliveredAt = models.DateTimeField(auto_now_add=False, null=True, blank=True)
    createdAt = models.DateField(auto_now_add=True )
    shippingAddress = models.ForeignKey(ShippingAddress, on_delete=models.CASCADE, related_name='orders')

    def __str__(self):
        return str(self.createdAt)


class OrderItem(models.Model):
    product = models.ForeignKey(Product, on_delete=models.SET_NULL, null=True, related_name='orderItems')
    order = models.ForeignKey(Order, on_delete=models.CASCADE, related_name='orderItems')
    qty = models.IntegerField(default=1)

    def __str__(self):
        return str(self.product.name + " " + str(self.qty))



