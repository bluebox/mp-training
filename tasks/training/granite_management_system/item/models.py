from django.db import models

from customer.models import Customer


# Create your models here.

class GraniteStore(models.Model):  # 1. model for granite stores data

    store_id = models.IntegerField(primary_key=True)
    store_name = models.CharField(max_length=50)
    established_year = models.IntegerField()
    store_description = models.CharField(max_length=250)
    contact = models.IntegerField()
    website = models.CharField(max_length=150)
    address = models.CharField(max_length=250)

    def __str__(self):
        return self.store_name

class Item(models.Model):  # 6. model to for items data

    item_id = models.IntegerField(primary_key=True)
    item_name = models.CharField(max_length=50)
    item_pattern = models.CharField(max_length=30)
    item_description = models.CharField(max_length=150)

    class Meta:
        db_table = "granite_mart_item"

    def __str__(self):
        return self.item_name

class ContainsItem(models.Model):  # 7. model for stores containing items

    contains_id = models.IntegerField(primary_key=True)
    store_id = models.ForeignKey(GraniteStore, on_delete=models.CASCADE)
    item_id = models.ForeignKey(Item, on_delete=models.CASCADE)
    price = models.FloatField()

    def __str__(self):
        return str(self.item_id)+" "+str(self.contains_id)+" "+str(self.store_id)

    class Meta:
        db_table = "granite_mart_containsitem"


class Cart(models.Model):
    customer_id = models.ForeignKey(Customer, on_delete=models.CASCADE)
    item_id = models.ForeignKey(ContainsItem, on_delete=models.CASCADE)
    quantity = models.IntegerField(default=1)

    class Meta:
        db_table = 'cart'

    def __str__(self):
        return str(self.customer_id)+' '+str(self.item_id)
