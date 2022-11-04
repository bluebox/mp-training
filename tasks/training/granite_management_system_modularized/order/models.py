from django.db import models

from customer.models import Customer
from item.models import ContainsItem

from vehicle.models import Vehicle

from employee.models import Employee


# Create your models here.
class Order(models.Model):  # 8. model for Order information

    order_id = models.IntegerField(primary_key=True)
    customer_id = models.ForeignKey(Customer, on_delete=models.CASCADE)
    order_time = models.DateTimeField()
    delivery_address = models.TextField(max_length=250, default="")

    class Meta:
        db_table = "granite_mart_order"


class OrderedItems(models.Model):  # 9. model for items per Order

    order_id = models.ForeignKey(Order, on_delete=models.CASCADE)
    contains_id = models.ForeignKey(ContainsItem, on_delete=models.CASCADE)

    class Meta:
        db_table = "granite_mart_ordereditems"

class DeliveryT(models.Model):
    order_id = models.OneToOneField(Order, on_delete=models.CASCADE)
    vehicle_id = models.OneToOneField(Vehicle, on_delete=models.CASCADE)
    driver_id = models.OneToOneField(Employee, on_delete=models.CASCADE)
    finsihed = models.BooleanField(default=True)

