# Generated by Django 4.0.5 on 2022-09-09 04:43

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('employee', '0002_alter_employee_emp_pic'),
    ]

    operations = [
        migrations.AddField(
            model_name='employee_devices',
            name='device_name',
            field=models.CharField(max_length=30, null=True),
        ),
        migrations.AddField(
            model_name='employee_devices',
            name='emp_name',
            field=models.CharField(max_length=30, null=True),
        ),
    ]