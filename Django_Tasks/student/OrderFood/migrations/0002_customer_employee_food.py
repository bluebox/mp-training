# Generated by Django 4.1 on 2022-09-05 09:46

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('OrderFood', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Customer',
            fields=[
                ('customer_id', models.CharField(max_length=10, primary_key=True, serialize=False)),
                ('customer_name', models.CharField(max_length=30)),
                ('customer_address', models.CharField(max_length=100)),
                ('customer_username', models.CharField(max_length=30)),
                ('customer_password', models.CharField(max_length=30)),
                ('customer_phn', models.CharField(max_length=20)),
                ('customer_email', models.CharField(max_length=40)),
            ],
        ),
        migrations.CreateModel(
            name='Employee',
            fields=[
                ('emp_id', models.CharField(max_length=10, primary_key=True, serialize=False)),
                ('emp_name', models.CharField(max_length=30)),
                ('emp_avg_rating', models.DecimalField(decimal_places=1, max_digits=2)),
                ('emp_username', models.CharField(max_length=30)),
                ('emp_password', models.CharField(max_length=30)),
                ('emp_phn', models.CharField(max_length=20)),
                ('emp_email', models.CharField(max_length=40)),
                ('is_available', models.BooleanField()),
            ],
        ),
        migrations.CreateModel(
            name='Food',
            fields=[
                ('food_id', models.CharField(max_length=10, primary_key=True, serialize=False)),
                ('food_name', models.CharField(max_length=30)),
                ('food_price', models.DecimalField(decimal_places=2, max_digits=5)),
                ('food_desc', models.CharField(max_length=100)),
                ('food_photo', models.CharField(max_length=50)),
            ],
        ),
    ]