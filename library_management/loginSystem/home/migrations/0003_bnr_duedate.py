# Generated by Django 4.1 on 2022-08-23 05:51

import datetime
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('home', '0002_bnr_books'),
    ]

    operations = [
        migrations.AddField(
            model_name='bnr',
            name='DueDate',
            field=models.DateTimeField(default=datetime.datetime.now),
        ),
    ]