# Generated by Django 4.1.1 on 2022-10-06 12:17

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('facilities', '0008_rename_equip_equipmentsrentedforbookingid_equip_id_and_more'),
    ]

    operations = [
        migrations.AlterField(
            model_name='equipment',
            name='equip_name',
            field=models.CharField(max_length=255, unique=True),
        ),
    ]