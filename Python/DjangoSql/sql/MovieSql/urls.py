from django.urls import  path

from .views import bulk_insert, transaction_example

urlpatterns=[
    path('bulk_insert/',bulk_insert,name='bulk_insert'),
    path('transaction_example/',transaction_example,name='transaction_example'),
]