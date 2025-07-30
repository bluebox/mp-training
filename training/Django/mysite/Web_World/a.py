import os
import django

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'mysite.mysite.settings')

django.setup()

from .models import  *

a=Books.objects.create(title="wings of fire",price=256.36)
a.save()