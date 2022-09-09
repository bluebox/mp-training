from atexit import register
from django.contrib import admin
from .models import movies
# Register your models here.
admin.site.register(movies)