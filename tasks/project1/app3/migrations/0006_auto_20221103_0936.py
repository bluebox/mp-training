# Generated by Django 2.2.4 on 2022-11-03 09:36

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('app3', '0005_mymodel'),
    ]

    operations = [
        migrations.AlterField(
            model_name='biodata1',
            name='id',
            field=models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID'),
        ),
        migrations.AlterField(
            model_name='mymodel',
            name='id',
            field=models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID'),
        ),
    ]