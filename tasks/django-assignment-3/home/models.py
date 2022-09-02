from email.policy import default
from inspect import classify_class_attrs
from django.db import models
import uuid
from django.contrib.auth.models import User

# Create your models here.

class Blog(models.Model):
     title = models.CharField(max_length=30)
     content = models.TextField()
     last_updated = models.DateTimeField(auto_now_add=True)
     author = models.ForeignKey(User, on_delete=models.CASCADE)

     def __str__(self) -> str:
          return self.title

class Profile(models.Model):
     user = models.OneToOneField(User, on_delete=models.CASCADE)
     image = models.ImageField(default='default.jpg', upload_to='profile_pics')

     def __str__(self):
          return f'{self.user.username} profile'