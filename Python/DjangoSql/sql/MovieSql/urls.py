from django.urls import  path

from .views import bulk_insert, transaction_example, aggregation_example, get_csrf_token, update_example, \
    delete_example, Q_example, Delete_from_Director, Delete_from_User, select_where_order_by_limit, joins_example

urlpatterns=[
    path('bulk_insert/',bulk_insert,name='bulk_insert'),
    path('transaction_example/',transaction_example,name='transaction_example'),
    path('aggregation_example/',aggregation_example,name='aggregation_example'),
    path('get_csrf_token/',get_csrf_token,name='get_csrf_token'),
    path('update_example/',update_example,name='update_example'),
    path('<int:pk>/',delete_example,name='delete_example'),
    path('Q_example/',Q_example,name='Q_example'),
    path('Delete_from_Director/',Delete_from_Director,name='Delete_from_Director'),
    path('Delete_from_User/',Delete_from_User,name='Delete_from_User'),
    path('select_where_order_by_limit/',select_where_order_by_limit,name='select_where_order_by_limit'),
    path('joins_example/',joins_example,name='joins_example'),
]