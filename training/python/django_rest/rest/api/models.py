import uuid
from django.db import models
from django.contrib.auth.models import AbstractUser
from .validators import NameValidator
# Create your models here.

class user(AbstractUser):
    pass


class product(models.Model):
    description = models.TextField()
    image = models.ImageField(upload_to='products/',blank=True,null=True)
    name = models.CharField(max_length=100,validators=[NameValidator,])
    price = models.DecimalField(max_digits = 10,decimal_places=2)
    stock = models.PositiveIntegerField()
    class Meta:
        db_table = 'product'



class order(models.Model):
    class order_status(models.TextChoices):
        PENDING = 'P'
        COMPLETED = 'C'
        REJECTED = 'R'
    order_id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    user = models.ForeignKey(user, on_delete=models.CASCADE)
    product = models.ManyToManyField(product, through="order_items",related_name='orders')
    created_at  = models.DateTimeField(auto_now_add= True)
    status = models.CharField(choices=order_status.choices,max_length=1,default='P')
    class Meta:
        db_table = 'order'

class order_items(models.Model):
    order = models.ForeignKey(order,on_delete=models.CASCADE)
    product = models.ForeignKey(product,on_delete=models.CASCADE)
    quantity = models.IntegerField()
    class Meta:
        db_table = 'order_items'
    @property
    def total_price(self):
        return self.product.price * self.quantity


