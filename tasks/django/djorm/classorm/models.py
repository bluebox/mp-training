from django.db import models

# Create your models here.
class Timeline(models.Model):
    """Timeline Model"""
    full_date = models.DateField(db_column='full_date', max_length=10, null=False)
    timeline_uid = models.BigIntegerField(db_column='timeline_uid', unique=True, primary_key=True)
    year = models.IntegerField(db_column='year', null=False)
    month = models.IntegerField(db_column='month', null=False)
    month_name = models.CharField(db_column='month_name', max_length=10)
    date = models.IntegerField(db_column='date', null=False)
    day_name = models.CharField(db_column='day_name', max_length=10)
    days_in_month = models.IntegerField(db_column='days_in_month')
    day_of_year = models.IntegerField(db_column='day_of_year', null=False)
    week_of_year = models.IntegerField(db_column='week_of_year', null=False)
    quarter_of_year = models.IntegerField(db_column='quarter_of_year')
    month_of_year = models.IntegerField(db_column='month_of_year', null=False)
    quarter_id = models.CharField(db_column='quarter_id', max_length=10)
    month_quarter_id = models.CharField(db_column='month_quarter_id', max_length=200)

    class Meta:
        """Meta"""
        managed = True
        db_table = 'timeline'
        index_together = [
            ['year', 'month', 'date']
        ]
