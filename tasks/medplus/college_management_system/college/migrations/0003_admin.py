# Generated by Django 4.0.5 on 2022-09-06 09:14

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('college', '0002_professor_password_students_password'),
    ]

    operations = [
        migrations.CreateModel(
            name='admin',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('admin_id', models.IntegerField()),
                ('password', models.CharField(max_length=10)),
            ],
        ),
    ]