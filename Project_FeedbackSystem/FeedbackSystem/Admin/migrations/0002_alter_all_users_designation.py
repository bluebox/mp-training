# Generated by Django 4.1.1 on 2022-09-09 07:13

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('Admin', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='all_users',
            name='Designation',
            field=models.CharField(default='', max_length=25),
        ),
    ]