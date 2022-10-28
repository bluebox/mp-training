from email.policy import default
from django.db import models
from django.contrib.auth.models import AbstractUser


class User(AbstractUser):
    u_name = models.CharField(max_length=255)
    f_name=models.CharField(max_length=255)
    l_name=models.CharField(max_length=255)
    email_id = models.CharField(max_length=255, unique=True)
    password = models.CharField(max_length=255)
    profile =models.TextField(null=True)
    subscription=models.BooleanField(default=False)
    username = None

    USERNAME_FIELD = 'email_id'
    REQUIRED_FIELDS = []
