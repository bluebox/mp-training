from django.db import models

# Create your models here.


class Restaurant(models.Model):
    restaurant_id=models.CharField(max_length=10,primary_key=True)
    restaurant_name=models.CharField(max_length=30)
    restaurant_location=models.CharField(max_length=100)
    restaurant_rating=models.DecimalField(max_digits = 2,decimal_places = 1)
    restaurant_username=models.CharField(max_length=30)
    restaurant_password=models.CharField(max_length=30)
    restaurant_phn=models.CharField(max_length=20)

    def __str__(self):
        return self.restaurant_name


class Customer(models.Model):
    customer_id=models.CharField(max_length=10,primary_key=True)
    customer_name = models.CharField(max_length=30)
    customer_address = models.CharField(max_length=100)
    customer_username = models.CharField(max_length=30)
    customer_password = models.CharField(max_length=30)
    customer_phn = models.CharField(max_length=20)
    customer_email = models.CharField(max_length=40)

    def __str__(self):
        return self.customer_name


class Employee(models.Model):
    emp_id=models.CharField(max_length=10,primary_key=True)
    emp_name = models.CharField(max_length=30)
    emp_avg_rating = models.DecimalField(max_digits = 2,decimal_places = 1)
    emp_username = models.CharField(max_length=30)
    emp_password = models.CharField(max_length=30)
    emp_phn = models.CharField(max_length=20)
    emp_email = models.CharField(max_length=40)
    is_available=models.BooleanField()

    def __str__(self):
        return self.emp_name


class Food(models.Model):
    food_id = models.CharField(max_length=10, primary_key=True)
    food_name = models.CharField(max_length=30)
    food_price = models.DecimalField(max_digits=5, decimal_places=2)
    food_desc = models.CharField(max_length=100)
    food_photo = models.CharField(max_length=50)

    def __str__(self):
        return self.food_name


class FoodAvailable(models.Model):
    restaurant_id=models.ForeignKey(Restaurant,on_delete=models.SET_NULL,null=True)
    food_id=models.ForeignKey(Food,on_delete=models.SET_NULL,null=True)
    is_available=models.BooleanField()

    def __str__(self):
        return self.food_id


class OrderFood(models.Model):
    order_food_id=models.CharField(max_length=10, primary_key=True)
    customer_id=models.ForeignKey(Customer,on_delete=models.SET_NULL,null=True)
    restaurant_id = models.ForeignKey(Restaurant, on_delete=models.SET_NULL, null=True)
    food_id = models.ForeignKey(Food, on_delete=models.SET_NULL, null=True)
    emp_id=models.ForeignKey(Employee, on_delete=models.SET_NULL, null=True)
    quantity = models.IntegerField()

    def __str__(self):
        return self.order_food_id


class Payment(models.Model):
    transaction_id = models.CharField(max_length=10, primary_key=True)
    order_food_id = models.ForeignKey(OrderFood, on_delete=models.SET_NULL, null=True)
    payment_type = models.CharField(max_length=50)
    payment_status = models.CharField(max_length=50)
    amount = models.IntegerField()

    def __str__(self):
        return self.transaction_id


class OrderDetails(models.Model):
    order_id = models.CharField(max_length=10, primary_key=True)
    order_food_id=models.ForeignKey(OrderFood, on_delete=models.SET_NULL, null=True)
    # customer_id = models.ForeignKey(Customer, on_delete=models.SET_NULL, null=True)
    # restaurant_id = models.ForeignKey(Restaurant, on_delete=models.SET_NULL, null=True)
    # emp_id = models.ForeignKey(Employee, on_delete=models.SET_NULL, null=True)
    transaction_id=models.ForeignKey(Payment, on_delete=models.SET_NULL, null=True)
    order_status=models.CharField(max_length=50)
    order_time=models.DateTimeField(auto_now_add=True)
    delivery_time = models.DateTimeField()

    def __str__(self):
        return self.order_id