# from django import forms
from django.db import models


class User(models.Model):
    user_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=50)
    mobile = models.IntegerField(default=0)
    password = models.CharField(max_length=30)


class Post(models.Model):
    post_id = models.AutoField(primary_key=True)
    mobile = models.CharField(max_length=10, unique=True,
                              error_messages={'unique': "This mobile number has already been registered."})
    email = models.EmailField(max_length=50, unique=True,
                              error_messages={'unique': "This email has already been registered."})
    name = models.CharField(max_length=50)
    images = models.CharField(max_length=200)
    likes = models.IntegerField(default=0)
    shares = models.IntegerField(default=0)
    comments = models.IntegerField(default=0)
    is_completed = models.BooleanField(default=False)
    score = models.IntegerField(default=0)
    date = models.DateTimeField(auto_now_add=True)
    is_winner = models.BooleanField(default=False)
    category = models.CharField(max_length=30, choices=[('cars', 'cars'), ('bikes', 'bikes'), ('animals', 'animals'),
                                                        ('technology', 'technology'), ('movies', 'movies')])

    class Meta:
        ordering = ['-score']
    def __str__(self):
        return str(self.likes) + str(self.likes) + str(self.comments) + str(self.score)


class Comments(models.Model):
    post_id = models.ForeignKey(Post, on_delete=models.CASCADE, related_query_name='post')
    comment_text = models.CharField(max_length=200)
    is_valid = models.BooleanField(default=False)
    date = models.DateTimeField(auto_now_add=True)


class Uploader(models.Model):
    name = models.CharField(max_length=30, null=True, blank=True)
    mobile = models.IntegerField(blank=True, null=True)
    email = models.CharField(max_length=50, default=None, blank=True)
