from django.db import models

# Create your models here.
class Product(models.Model):
    product_id = models.AutoField
    product_name = models.CharField(max_length=50)
    category = models.CharField(max_length=50, default="")
    subcategory = models.CharField(max_length=50,default="")
    price = models.IntegerField(default=0)
    desc = models.CharField(max_length=300)
    pub_date = models.DateField()
    image = models.ImageField(upload_to="shop/images", default="")
    def __str__(self):
        return self.product_name

class Contact(models.Model):
    msg_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=50)
    phone = models.CharField(max_length=50, default="")
    email = models.CharField(max_length=50, default="")
    message = models.CharField(max_length=500, default="")
    def __str__(self):
        return self.name


# class User(models.Model):
#     user_id = models.AutoField(primary_key=True)
#     name = models.CharField(max_length=50)
#     phone = models.CharField(max_length=50, default="")
#     email = models.CharField(max_length=50, default="")
#     password = models.CharField(max_length=20)
#     def __str__(self):
#         return self.name


