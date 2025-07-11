from django.urls import path
from .views import signup, get_by_id, get_data_exclude, get_data_ordered_by_asc, get_data_ordered_by_desc, Q_data, \
    field_lookups, update_single_row, update_multiple_rows, delete_single_row, delete_multiple_rows, aggretaion

urlpatterns = [
    path('signup/',signup,name='signup'),
    path('<int:pk>/',get_by_id,name='data'),
    # path('<str:value>/',get_data_filter,name='name'),
    path('age/<int:age>/',get_data_exclude,name='age'),
    path('data/get_data/',get_data_ordered_by_asc,name='all data'),
    path('data/get_data_desc/',get_data_ordered_by_desc,name='all data'),
    path('data/Q_data/',Q_data,name='Q_data'),
    path('data/field_lookups/',field_lookups,name='field_lookups'),
    path('update_single_row/',update_single_row,name='update_single_row'),
    path('update_multiple_rows/',update_multiple_rows,name="update_multiple_rows"),
    path('delete_single_row/',delete_single_row,name='delete_single_row'),
    path('delete_multiple_rows/',delete_multiple_rows,name='delete_multiple_rows'),
    path('aggretaion/',aggretaion,name='aggretaion')
]
