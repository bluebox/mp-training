# Generated by Django 4.0.5 on 2022-08-24 11:37

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('medplus_app', '0005_employees'),
    ]

    operations = [
        migrations.DeleteModel(
            name='employees',
        ),
        migrations.DeleteModel(
            name='Geeks',
        ),
        migrations.DeleteModel(
            name='GeeksModel',
        ),
    ]