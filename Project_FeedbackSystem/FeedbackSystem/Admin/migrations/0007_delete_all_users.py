# Generated by Django 4.1.1 on 2022-09-09 07:18

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('Admin', '0006_all_users_designation_all_users_first_name_and_more'),
    ]

    operations = [
        migrations.DeleteModel(
            name='All_Users',
        ),
    ]