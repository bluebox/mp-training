# Generated by Django 4.1.1 on 2022-09-12 07:32

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('Admin', '0011_classes_details'),
    ]

    operations = [
        migrations.AlterField(
            model_name='classes_details',
            name='class_code',
            field=models.CharField(max_length=6, primary_key=True, serialize=False),
        ),
        migrations.AlterField(
            model_name='classes_details',
            name='class_name',
            field=models.CharField(max_length=35),
        ),
    ]