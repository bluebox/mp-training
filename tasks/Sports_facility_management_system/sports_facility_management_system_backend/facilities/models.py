from django.db import models

# Create your models here.


class FacilityDetail(models.Model):
    facility_id = models.AutoField(primary_key=True)
    facility_name = models.CharField(max_length=255)
    facility_location = models.CharField(max_length=255)
    facility_phone = models.CharField(unique=True, max_length=255)
    facility_email = models.CharField(max_length=255)
    facility_password = models.CharField(max_length=255)

    def __str__(self):
        return self.facility_name


class Sport(models.Model):
    sport_id = models.AutoField(primary_key=True)
    sport_name = models.CharField(max_length=255)
    facility = models.ManyToManyField(FacilityDetail, through='SportsInFacility')

    def __str__(self):
        return self.sport_name


class SportsInFacility(models.Model):
    facility_sport_id = models.AutoField(primary_key=True)
    facility = models.ForeignKey(FacilityDetail, on_delete=models.CASCADE)
    sport = models.ForeignKey(Sport, on_delete=models.CASCADE)
    cost_per_slot = models.IntegerField()

    class Meta:
        unique_together = [['facility', 'sport']]


class Slot(models.Model):
    slot_id = models.AutoField(primary_key=True)
    slot_time = models.CharField(max_length=255)
    sport_facility = models.ManyToManyField(SportsInFacility, through='SlotsInSportFacility')

    def __str__(self):
        return self.slot_time


class SlotsInSportFacility(models.Model):
    id = models.AutoField(primary_key=True)
    slot = models.ForeignKey(Slot, on_delete=models.CASCADE)
    facility_sport = models.ForeignKey(SportsInFacility, on_delete=models.CASCADE)


class Equipment(models.Model):
    equip_id = models.AutoField(primary_key=True)
    equip_name = models.CharField(max_length=255)
    sport = models.ForeignKey(Sport, on_delete=models.CASCADE)
    rent_per_slot = models.IntegerField()

    def __str__(self):
        return self.equip_name


class User(models.Model):
    user_id = models.AutoField(primary_key=True)
    user_name = models.CharField(max_length=255)
    user_phone = models.CharField(unique=True, max_length=255)
    user_email = models.CharField(max_length=255)
    user_password = models.CharField(max_length=255)


class BookingData(models.Model):
    booking_id = models.AutoField(primary_key=True)
    facility_sport_id = models.ForeignKey(SportsInFacility, models.DO_NOTHING)
    user = models.ForeignKey(User, models.DO_NOTHING)
    date_time = models.CharField(max_length=255)
    reviews = models.TextField(blank=True, null=True)
    ratings = models.IntegerField(blank=True, null=True)


class SlotsBookedForBookingId(models.Model):
    booking_id = models.ForeignKey(BookingData, models.DO_NOTHING)
    slot_id = models.ForeignKey(Slot, models.DO_NOTHING)


class EquipmentsRentedForBookingId(models.Model):
    booking_id = models.ForeignKey(BookingData, models.DO_NOTHING)
    equip = models.ForeignKey(Equipment, models.DO_NOTHING)


class Invoice(models.Model):
    invoice_id = models.AutoField(primary_key=True)
    booking_id = models.ForeignKey(BookingData, models.DO_NOTHING)
    total_cost = models.IntegerField()
    invoice_pdf = models.TextField()
