from django.db import models
from django.db.models import Q
# from django.contrib.auth.models import User
from django.contrib.auth import get_user_model
User = get_user_model()
# Create your models here.




class ThreadManager(models.Manager):
    def by_user(self, **kwargs):
        user = kwargs.get('user')
        lookup = Q(first_person=user) | Q(second_person=user)
        qs = self.get_queryset().filter(lookup).distinct()
        return qs

        
class ThreadingTable(models.Model):
    first_person = models.ForeignKey(User, on_delete=models.CASCADE, null=True, blank=True, related_name='Thread1')
    second_person = models.ForeignKey(User, on_delete=models.CASCADE, null=True, related_name='Thread2')
    update_at = models.DateTimeField(auto_now=True)
    timestamp = models.DateTimeField(auto_now_add=True)
    objects =   ThreadManager()
    class Meta:
        unique_together =['first_person', 'second_person']


        



class Messages1(models.Model):
    thread = models.ForeignKey(ThreadingTable, null=True, blank=True, on_delete=models.CASCADE, related_name='message1')
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    message =models.TextField()
    timestamp = models.DateTimeField(auto_now_add=True)
    seen = models.BooleanField(default=False )





