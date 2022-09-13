from django.contrib import admin
from .models import  Batsman,Matches, Bowler

# Register your models here.
admin.site.register(Batsman)
admin.site.register(Bowler)
admin.site.register(Matches)