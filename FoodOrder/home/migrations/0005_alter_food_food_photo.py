# Generated by Django 4.1.1 on 2022-09-26 09:40

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('home', '0004_restaurant_restaurant_email_and_more'),
    ]

    operations = [
        migrations.AlterField(
            model_name='food',
            name='food_photo',
            field=models.TextField(max_length=50, null=True),
        ),
    ]