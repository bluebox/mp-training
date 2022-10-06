from django.contrib import admin

# Register your models here.
from .models import *

admin.site.register(FacilityDetail)
admin.site.register(Sport)
admin.site.register(SportsInFacility)
admin.site.register(Slot)
admin.site.register(Equipment)
admin.site.register(SlotsInSportFacility)
admin.site.register(BookingData)
admin.site.register(User)
admin.site.register(SlotsBookedForBookingId)
admin.site.register(EquipmentsRentedForBookingId)
admin.site.register(Invoice)
