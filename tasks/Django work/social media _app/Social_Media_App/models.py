from operator import mod
from pyexpat import model
from tkinter.tix import Tree
from django.db import models
from django.contrib.auth import get_user_model
import uuid
from datetime import datetime
User = get_user_model()
# Create your models here.
class Profile(models.Model):
    user = models.ForeignKey(User, on_delete= models.CASCADE)
    id_user = models.IntegerField()
    bio = models.TextField(blank = True)
    profile_img = models.ImageField(upload_to = 'profile_img', default ='blank-profile-picture.png')
    location = models.CharField(max_length=100, blank=True)
    working = models.CharField(max_length=100, default=True)
    def __str__(self) -> str:
        return self.user.username


class posting(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4)
    user =models.CharField(max_length=100)
    image = models.ImageField(upload_to = "posting_images")
    caption =models.TextField()
    created_at =models.DateTimeField(default=datetime.now)
    likes=models.IntegerField(default=0)
    def __str__(self) -> str:
        return self.user
class likepost(models.Model):
    post_id = models.CharField(max_length=100)
    username = models.CharField(max_length=100)
    def __str__(self) -> str:
        return self.username

class followerscount(models.Model):
    follower = models.CharField(max_length=100)
    user =models.CharField(max_length=100)
    def __str__(self) -> str:
        return self.user
