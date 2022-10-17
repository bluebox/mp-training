from django.db import models
from django.core.exceptions import ValidationError
# Create your models here.
from django.contrib.auth.models import BaseUserManager, AbstractBaseUser


class UserManager(BaseUserManager):

    def create_user(self, email, password=None):

        if not email:
            raise ValueError('Users Must Have an email address')

        user = self.model(
            email=self.normalize_email(email),
        )
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, email, password):

        if password is None:
            raise TypeError('Superusers must have a password.')

        user = self.create_user(email, password)
        user.is_superuser = True
        user.save()
        return user

    def create_customeruser(self, email, password):
        if password is None:
            raise TypeError('cus must have a password')
        user = self.create_user(email, password)
        user.is_customer = True
        user.save()
        return user

    def create_restaurantuser(self, email, password):
        if password is None:
            raise TypeError('res must have a password')
        user = self.create_user(email, password)
        user.is_restaurant = True
        user.save()
        return user

    def create_empuser(self, email, password):
        if password is None:
            raise TypeError('emp must have a password')
        user = self.create_user(email, password)
        user.is_emp = True
        user.save()
        return user


class User(AbstractBaseUser):
    email = models.EmailField(
        verbose_name='email address',
        max_length=255,
        unique=True
    )
    is_active = models.BooleanField(default=True)
    is_customer = models.BooleanField(default=False)
    is_superuser = models.BooleanField(default=False)
    is_restaurant = models.BooleanField(default=False)
    is_emp = models.BooleanField(default=False)
    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = []

    # Tells Django that the UserManager class defined above should manage
    # objects of this type.
    objects = UserManager()

    def __str__(self):
        return self.email

    def has_perm(self, perm, obj=None):
        "Does the user have a specific permission?"
        # Simplest possible answer: Yes, always
        return True

    def has_module_perms(self, app_label):
        "Does the user have permissions to view the app `app_label`?"
        # Simplest possible answer: Yes, always
        return True

    @property
    def is_staff(self):
        return self.is_superuser
    class Meta:
        db_table = "user"
        managed = True
def validate_mail(value):
    if "@gmail.com" in value:
        return value
    else:
        raise ValidationError("Enter a valid mail")


class Restaurant(models.Model):
    restaurant_id = models.CharField(max_length=10, primary_key=True)
    user = models.OneToOneField(User, on_delete=models.CASCADE, related_name='restaurant', null=True)
    restaurant_name = models.CharField(max_length=30)
    restaurant_address1 = models.CharField(max_length=100, default=None)
    restaurant_address2 = models.CharField(max_length=100, default=None, null=True)
    restaurant_city = models.CharField(max_length=100, default=None)
    restaurant_state = models.CharField(max_length=100, default=None)
    restaurant_code = models.CharField(max_length=100, default=None)
    restaurant_avg_rating = models.DecimalField(max_digits=2, decimal_places=1, null=True)
    restaurant_username = models.CharField(max_length=30)
    # restaurant_password = models.CharField(max_length=30)
    restaurant_phn = models.CharField(max_length=20, unique=True)
    restaurant_avaibility = models.CharField(max_length=20, null=True)
    # email = models.CharField(max_length=40, validators=[validate_mail], unique=True, default=None)
    open_timing = models.CharField(max_length=20, null=True)
    close_timing = models.CharField(max_length=20, null=True)
    restaurant_photo = models.TextField(max_length=500, null=True)

    def __str__(self):
        return self.restaurant_id


class Reviews(models.Model):
    review_id = models.CharField(max_length=10, primary_key=True)
    restaurant_id = models.ForeignKey(Restaurant, on_delete=models.SET_NULL, null=True)
    review = models.CharField(max_length=100)
    rating = models.DecimalField(max_digits=2, decimal_places=1)


class Customer(models.Model):
    customer_id = models.CharField(max_length=10, primary_key=True)
    user = models.OneToOneField(User, on_delete=models.CASCADE, related_name='Customer', null=True)
    customer_name = models.CharField(max_length=30)
    customer_address1 = models.CharField(max_length=100, default=None)
    customer_address2 = models.CharField(max_length=100, default=None, null=True)
    customer_city = models.CharField(max_length=100, default=None)
    customer_state = models.CharField(max_length=100, default=None)
    customer_code = models.CharField(max_length=100, default=None)
    customer_username = models.CharField(max_length=30, unique=True)
    # customer_password = models.CharField(max_length=30)
    customer_phn = models.CharField(max_length=20, unique=True)
    # customer_email = models.CharField(max_length=40, validators=[validate_mail], unique=True)


class Employee(models.Model):
    emp_id = models.CharField(max_length=10, primary_key=True)
    user = models.OneToOneField(User, on_delete=models.CASCADE, related_name='Employee', null=True)
    emp_name = models.CharField(max_length=30)
    emp_username = models.CharField(max_length=30, unique=True)
    # emp_password = models.CharField(max_length=30)
    emp_phn = models.CharField(max_length=20, unique=True)
    # emp_email = models.CharField(max_length=40, validators=[validate_mail], unique=True)
    is_available = models.BooleanField(default=False, null=True)

    def __str__(self):
        return self.emp_name


class Food(models.Model):
    food_id = models.CharField(max_length=10, primary_key=True)
    restaurant_id = models.ForeignKey(Restaurant, on_delete=models.SET_NULL, null=True)
    food_name = models.CharField(max_length=30)
    food_price = models.DecimalField(max_digits=5, decimal_places=2, null=True)
    food_desc = models.CharField(max_length=100)
    food_photo = models.TextField(max_length=500, null=True)
    is_available = models.BooleanField(default=False)

    # quantity = models.IntegerField(null=True,default=1)
    def __str__(self):
        return self.food_name


class Menu(models.Model):
    menu_id = models.CharField(max_length=10, primary_key=True)
    menu_type = models.CharField(max_length=30)
    restaurant_id = models.ForeignKey(Restaurant, on_delete=models.SET_NULL, null=True)

    is_available = models.BooleanField(default=False)

    def __str__(self):
        return self.menu_id


class MenuList(models.Model):
    menu_id = models.ForeignKey(Menu, on_delete=models.SET_NULL, null=True)
    food_id = models.ForeignKey(Food, on_delete=models.SET_NULL, null=True)


class OrderFood(models.Model):
    order_food_id = models.CharField(max_length=10, primary_key=True)
    customer_id = models.ForeignKey(Customer, on_delete=models.SET_NULL, null=True)
    restaurant_id = models.ForeignKey(Restaurant, on_delete=models.SET_NULL, null=True)
    food_id = models.ForeignKey(Food, on_delete=models.SET_NULL, null=True)
    # emp_id=models.ForeignKey(Employee, on_delete=models.SET_NULL, null=True)
    quantity = models.IntegerField()
    price = models.IntegerField(null=True)
    menu_id = models.ForeignKey(Menu, on_delete=models.SET_NULL, null=True)

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
    order_food_id = models.ForeignKey(OrderFood, on_delete=models.SET_NULL, null=True)
    customer_id = models.ForeignKey(Customer, on_delete=models.SET_NULL, null=True)
    restaurant_id = models.ForeignKey(Restaurant, on_delete=models.SET_NULL, null=True)
    emp_id = models.ForeignKey(Employee, on_delete=models.SET_NULL, null=True)
    transaction_id = models.ForeignKey(Payment, on_delete=models.SET_NULL, null=True)
    order_status = models.CharField(max_length=50)
    order_time = models.DateTimeField(auto_now_add=True)
    delivery_time = models.DateTimeField()

    def __str__(self):
        return self.order_id
