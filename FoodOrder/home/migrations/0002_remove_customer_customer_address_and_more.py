# Generated by Django 4.1.1 on 2022-09-23 09:46

from django.db import migrations, models
import home.models


class Migration(migrations.Migration):

    dependencies = [
        ('home', '0001_initial'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='customer',
            name='customer_address',
        ),
        migrations.RemoveField(
            model_name='restaurant',
            name='restaurant_location',
        ),
        migrations.AddField(
            model_name='customer',
            name='customer_address1',
            field=models.CharField(default=None, max_length=100),
        ),
        migrations.AddField(
            model_name='customer',
            name='customer_address2',
            field=models.CharField(default=None, max_length=100),
        ),
        migrations.AddField(
            model_name='customer',
            name='customer_city',
            field=models.CharField(default=None, max_length=100),
        ),
        migrations.AddField(
            model_name='customer',
            name='customer_code',
            field=models.CharField(default=None, max_length=100),
        ),
        migrations.AddField(
            model_name='customer',
            name='customer_state',
            field=models.CharField(default=None, max_length=100),
        ),
        migrations.AddField(
            model_name='restaurant',
            name='restaurant_address1',
            field=models.CharField(default=None, max_length=100),
        ),
        migrations.AddField(
            model_name='restaurant',
            name='restaurant_address2',
            field=models.CharField(default=None, max_length=100),
        ),
        migrations.AddField(
            model_name='restaurant',
            name='restaurant_city',
            field=models.CharField(default=None, max_length=100),
        ),
        migrations.AddField(
            model_name='restaurant',
            name='restaurant_code',
            field=models.CharField(default=None, max_length=100),
        ),
        migrations.AddField(
            model_name='restaurant',
            name='restaurant_state',
            field=models.CharField(default=None, max_length=100),
        ),
        migrations.AlterField(
            model_name='customer',
            name='customer_email',
            field=models.CharField(max_length=40, validators=[home.models.validate_mail]),
        ),
        migrations.AlterField(
            model_name='employee',
            name='emp_email',
            field=models.CharField(max_length=40, validators=[home.models.validate_mail]),
        ),
    ]