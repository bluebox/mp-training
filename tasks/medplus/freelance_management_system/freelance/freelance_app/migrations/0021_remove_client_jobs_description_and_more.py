# Generated by Django 4.0.5 on 2022-09-23 07:44

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('freelance_app', '0020_alter_freelancer_details_phone_number'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='client_jobs',
            name='description',
        ),
        migrations.AlterField(
            model_name='client_contract_details',
            name='contract_id',
            field=models.AutoField(primary_key=True, serialize=False),
        ),
        migrations.AlterField(
            model_name='client_details',
            name='client_id',
            field=models.AutoField(primary_key=True, serialize=False),
        ),
        migrations.AlterField(
            model_name='client_details',
            name='email_id',
            field=models.EmailField(max_length=254),
        ),
        migrations.AlterField(
            model_name='client_details',
            name='phone_number',
            field=models.CharField(max_length=12),
        ),
        migrations.AlterField(
            model_name='client_jobs',
            name='job_id',
            field=models.AutoField(primary_key=True, serialize=False),
        ),
        migrations.AlterField(
            model_name='freelancer_details',
            name='email_id',
            field=models.EmailField(max_length=254),
        ),
        migrations.AlterField(
            model_name='freelancer_details',
            name='id',
            field=models.AutoField(primary_key=True, serialize=False),
        ),
        migrations.AlterField(
            model_name='freelancer_payment_details',
            name='payment_id',
            field=models.AutoField(primary_key=True, serialize=False),
        ),
        migrations.AlterField(
            model_name='freelancer_proposals',
            name='proprosal_id',
            field=models.AutoField(primary_key=True, serialize=False),
        ),
    ]