# Generated by Django 4.1.1 on 2022-10-04 09:53

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('appointment', '0003_appointment_tests'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='lab',
            name='lab_number',
        ),
    ]