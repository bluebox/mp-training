import django_filters
from .models import *
import mysql.connector as sql


class SomeFilter(django_filters.FilterSet):
    class Meta:
        model = StudentsList
        fields = ['dept']

