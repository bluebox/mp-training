# Generated by Django 4.0.5 on 2022-09-26 06:19

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('freelance_app', '0023_remove_client_jobs_description'),
    ]

    operations = [
        migrations.AlterField(
            model_name='client_contract_details',
            name='contract_id',
            field=models.AutoField(primary_key=True, serialize=False, unique=True),
        ),
        migrations.AlterField(
            model_name='client_details',
            name='client_id',
            field=models.AutoField(primary_key=True, serialize=False, unique=True),
        ),
        migrations.AlterField(
            model_name='client_jobs',
            name='job_id',
            field=models.AutoField(primary_key=True, serialize=False, unique=True),
        ),
        migrations.AlterField(
            model_name='freelancer_details',
            name='id',
            field=models.AutoField(primary_key=True, serialize=False, unique=True),
        ),
        migrations.AlterField(
            model_name='freelancer_payment_details',
            name='payment_id',
            field=models.AutoField(primary_key=True, serialize=False, unique=True),
        ),
        migrations.AlterField(
            model_name='freelancer_proposals',
            name='proprosal_id',
            field=models.AutoField(primary_key=True, serialize=False, unique=True),
        ),
    ]