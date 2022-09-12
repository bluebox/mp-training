from django.dispatch import receiver
from django.db.models.signals import post_save
from django.http import HttpResponse
from .models import leave,Team_lead

@receiver(post_save,sender = leave)
def team_lead_trig(sender,instance,created,**kwargs):
    if created:
        a = Team_lead.objects.get(id = 1)
        a.leave_request = a.leave_request + 1
        a.save()
    else:
        pass