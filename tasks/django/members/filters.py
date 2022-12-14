import django_filters
from .models import *

class BranchFilter(django_filters.FilterSet):
    class Meta:
        model = StudentInfo
        fields = ['branch', 'gender']