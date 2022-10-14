from django.urls import path
from . import views
# from rest_framework_simplejwt.views import TokenVerifyView

urlpatterns = [
    path('number_of_requests/', views.NumberOfRequests.as_view(), name='NumberOfRequests'),
    path('debit_create/', views.CreateDebitCard.as_view(), name='CreateDebitCard'),
    path('credit_create/', views.CreateCreditCard.as_view(), name='CreateCreditCard'),
    path('get_account_id/', views.GetAccountId.as_view(), name='GetAccountId'),
    path('delete_request/', views.DeleteRequest.as_view(), name='DeleteRequest'),
    path('emp_request_data/', views.EmployeeRequestData.as_view(), name='RequestData'),
    path('request_data/', views.RequestData.as_view(), name='RequestData'),
    path('cus_request/', views.CustomerRequestsView.as_view(), name='CustomerRequestsView'),
    path('get_cards/', views.GetCards.as_view(), name='GetCards'),
    path('image_upload/', views.ImageUpload.as_view(), name='ImageUpload'),
    path('upload_image/', views.uploadImage.as_view(), name='image_upload'),
    path('user_details/', views.userDetails.as_view(), name='userDetails'),
    path('user_details/<int:id>', views.userDetailsWithId.as_view(), name='userDetails'),
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
    path('customer_login/', views.CustomerTokenGeneration.as_view(), name='CustomerTokenGeneration'),


    # path('generic_view', views.GenericAPIView.as_view(), name='GenericAPIView'),
]