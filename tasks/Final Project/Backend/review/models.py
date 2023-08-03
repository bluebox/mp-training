from django.db import models
from base.models import Movie
from django.contrib.auth.models import User

# Create your models here.

class Review(models.Model):
    movie = models.ForeignKey(Movie, on_delete=models.SET_NULL, null=True)
    user = models.ForeignKey(User, on_delete=models.SET_NULL, null=True)
    name = models.CharField(max_length=200, null=True, blank=True)
    rating = models.IntegerField(null=True, blank=True, default=0)
    comment = models.TextField(null= True, blank=True)
    review_id = models.AutoField(primary_key=True, editable=False)
    createdAt = models.DateTimeField(auto_now_add=True, null=True, blank=True)

    def __str__(self):
        return str(self.user)
