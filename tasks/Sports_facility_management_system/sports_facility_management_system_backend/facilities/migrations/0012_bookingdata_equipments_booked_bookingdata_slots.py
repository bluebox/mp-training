# Generated by Django 4.1.1 on 2022-10-11 10:09

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('facilities', '0011_usertoken'),
    ]

    operations = [
        migrations.AddField(
            model_name='bookingdata',
            name='equipments_booked',
            field=models.ManyToManyField(blank=True, through='facilities.EquipmentsRentedForBookingId', to='facilities.equipment'),
        ),
        migrations.AddField(
            model_name='bookingdata',
            name='slots',
            field=models.ManyToManyField(through='facilities.SlotsBookedForBookingId', to='facilities.slot'),
        ),
    ]