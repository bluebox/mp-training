# Generated by Django 4.0.6 on 2022-09-12 12:49

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Timeline',
            fields=[
                ('full_date', models.DateField(db_column='full_date', max_length=10)),
                ('timeline_uid', models.BigIntegerField(db_column='timeline_uid', primary_key=True, serialize=False, unique=True)),
                ('year', models.IntegerField(db_column='year')),
                ('month', models.IntegerField(db_column='month')),
                ('month_name', models.CharField(db_column='month_name', max_length=10)),
                ('date', models.IntegerField(db_column='date')),
                ('day_name', models.CharField(db_column='day_name', max_length=10)),
                ('days_in_month', models.IntegerField(db_column='days_in_month')),
                ('day_of_year', models.IntegerField(db_column='day_of_year')),
                ('week_of_year', models.IntegerField(db_column='week_of_year')),
                ('quarter_of_year', models.IntegerField(db_column='quarter_of_year')),
                ('month_of_year', models.IntegerField(db_column='month_of_year')),
                ('quarter_id', models.CharField(db_column='quarter_id', max_length=10)),
                ('month_quarter_id', models.CharField(db_column='month_quarter_id', max_length=200)),
            ],
            options={
                'db_table': 'timeline',
                'managed': True,
                'index_together': {('year', 'month', 'date')},
            },
        ),
    ]