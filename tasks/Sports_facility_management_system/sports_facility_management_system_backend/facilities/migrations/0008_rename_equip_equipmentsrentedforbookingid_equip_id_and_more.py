# Generated by Django 4.1.1 on 2022-10-06 10:47

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('facilities', '0007_rename_facility_sport_slotsinsportfacility_facility_sport_id'),
    ]

    operations = [
        migrations.RenameField(
            model_name='equipmentsrentedforbookingid',
            old_name='equip',
            new_name='equip_id',
        ),
        migrations.AddField(
            model_name='equipmentsrentedforbookingid',
            name='quantity',
            field=models.IntegerField(default=1),
            preserve_default=False,
        ),
    ]