from django.urls import path
from . import views
# from rest_framework_simplejwt.views import TokenVerifyView

urlpatterns = [
    # path('restfr/', views.func_based_views, name='details'),
    # path('restframe/<str:name>/', views.func_based_views_with_pk, name='func_based_views_with_pk'),
    #
    # path('api_view/', views.func_based_api_views, name='api_view'),
    # path('api_view/<str:name>/', views.func_based_api_views_with_pk, name='func_based_api_views_with_pk'),
    #
    # path('api/token/verify/', TokenVerifyView.as_view(), name='token_verify'),
    path('transaction_list/', views.TransactionList.as_view(), name='viewBalance'),
    path('transfer_money/', views.MoneyTransfer.as_view(), name='viewBalance'),
    path('view_balance/', views.viewBalance.as_view(), name='viewBalance'),
    path('emp_login/', views.Login.as_view(), name='Login'),
    path('class_apiview/<int:id>/', views.ClassBasedViewsWithId.as_view(), name='ClassBasedViewsWithId'),
    path('class_apiview/', views.ClassBasedViews.as_view(), name='ClassBasedViews'),

    path('emp_apiview/<int:id>/', views.EmpClassBasedViewsWithId.as_view(), name='EmpClassBasedViewsWithId'),
    path('emp_apiview/', views.EmpClassBasedViews.as_view(), name='EmpClassBasedViews'),
    path('acc_create/', views.CustomerAccCreate.as_view(), name='CustomerAccCreate'),
    path('adding_account/', views.AddingAccount.as_view(), name='AddingAccount'),
    path('customer_loin/', views.CustomerTokenGeneration.as_view(), name='CustomerTokenGeneration'),


    # path('generic_view', views.GenericAPIView.as_view(), name='GenericAPIView'),
]