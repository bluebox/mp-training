from django.db import models
from django.db.models import CASCADE
from django.db.models.fields import CharField


class Blog(models.Model):
    name=models.CharField(max_length=255)
    channel=models.CharField(max_length=255)
    def __str__(self):
        return self.name
    class Meta:
        db_table='Blog'

class Comments(models.Model):
    blog=models.ForeignKey(Blog,on_delete=CASCADE,related_name='comments')
    comment=CharField(max_length=255)
    def __str__(self):
        return self.blog

