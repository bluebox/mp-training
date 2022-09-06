from django.contrib import admin
from .models import CricketBoard, Coach, Team, Batsman, Bowler,WicketKeeper, Captain,Player

# Register your models here.
admin.site.register(CricketBoard)
admin.site.register(Coach)
admin.site.register(Captain)
admin.site.register(Team)
admin.site.register(Batsman)
admin.site.register(Bowler)
admin.site.register(WicketKeeper)
admin.site.register(Player)