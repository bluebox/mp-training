# Generated by Django 4.1 on 2022-09-29 04:24

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('app4', '0004_alter_schedule_flight_id'),
    ]

    operations = [
        migrations.AddField(
            model_name='passenger',
            name='password',
            field=models.CharField(blank=True, max_length=50, null=True),
        ),
    ]