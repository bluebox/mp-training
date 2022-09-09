# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Facility(models.Model):
    facility_id = models.AutoField(primary_key=True)
    facility_name = models.CharField(max_length=255)
    facility_location = models.CharField(max_length=255)
    facility_phone = models.CharField(unique=True, max_length=255)
    facility_email = models.CharField(max_length=255)
    facility_password = models.CharField(max_length=255)


class FacilitySports(models.Model):
    facility_sport_id = models.AutoField(primary_key=True)
    facility = models.ForeignKey(Facility, models.DO_NOTHING, )
    sport = models.ForeignKey('Sports', models.DO_NOTHING, )
    cost_per_slot = models.IntegerField()


class User(models.Model):
    user_id = models.AutoField(primary_key=True)
    user_name = models.CharField(max_length=255)
    user_phone = models.CharField(unique=True, max_length=255)
    user_email = models.CharField(max_length=255)
    user_password = models.CharField(max_length=255)


class BookingData(models.Model):
    booking_id = models.AutoField(primary_key=True)
    facility_sport = models.ForeignKey(FacilitySports, models.DO_NOTHING)
    user = models.ForeignKey(User, models.DO_NOTHING)
    date_time = models.DateTimeField()
    reviews = models.TextField(blank=True, null=True)
    ratings = models.IntegerField(blank=True, null=True)


class Equipment(models.Model):
    equip_id = models.AutoField(primary_key=True)
    equip_name = models.CharField(max_length=255)
    sport = models.ForeignKey('Sports', models.DO_NOTHING)
    rent_per_slot = models.IntegerField()


class EquipmentsRentedForBookingId(models.Model):
    booking = models.ForeignKey(BookingData, models.DO_NOTHING)
    equip = models.ForeignKey(Equipment, models.DO_NOTHING)


class Invoice(models.Model):
    invoice_id = models.AutoField(primary_key=True)
    booking = models.ForeignKey(BookingData, models.DO_NOTHING)
    total_cost = models.IntegerField()
    invoice_pdf = models.TextField()


class Slots(models.Model):
    slot_id = models.AutoField(primary_key=True)
    slot_time = models.TimeField()


class SlotsAvailableForFacilitySports(models.Model):
    facility_sport = models.ForeignKey(FacilitySports, models.DO_NOTHING)
    slot = models.ForeignKey(Slots, models.DO_NOTHING)


class SlotsBookedForBookingId(models.Model):
    booking = models.ForeignKey(BookingData, models.DO_NOTHING)
    slot = models.ForeignKey(Slots, models.DO_NOTHING)


class Sports(models.Model):
    sport_id = models.AutoField(primary_key=True)
    sport_name = models.CharField(max_length=255)

