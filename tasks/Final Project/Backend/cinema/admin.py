from django.contrib import admin
from .models import Cinema, City, Cinema_Hall, Cinema_Seat
# Register your models here.

admin.site.register(Cinema)
admin.site.register(City)
admin.site.register(Cinema_Hall)
admin.site.register(Cinema_Seat)

