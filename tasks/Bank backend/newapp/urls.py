from django.urls import path
from . import views
# from rest_framework_simplejwt.views import TokenVerifyView

urlpatterns = [
    path('get_data_by_search_loans/', views.GetDataBySearchLoans.as_view(), name='GetDataBySearchLoans'),
    path('get_data_by_search/', views.GetDataBySearch.as_view(), name='GetDataBySearch'),
    path('change_email/', views.ChangeEmail.as_view(), name='ChangeEmail'),
    path('change_phone_number/', views.ChangePhoneNumber.as_view(), name='ChangePhoneNumber'),
    path('emp_loans_list/', views.LoansListInEmployeeDashBoard.as_view(), name='LoansListInEmployeeDashBoard'),
    path('customer_count/', views.CustomerCount.as_view(), name='CustomerCount'),
    path('customer_list/', views.CustomerList.as_view(), name='CustomerList'),
    path('reject_loan/', views.RejectLoan.as_view(), name='RejectLoan'),
    path('loans_data/', views.LoanDetailsData.as_view(), name='LoanDetailsData'),
    path('accept_loan/', views.AcceptLoan.as_view(), name='AcceptLoan'),
    path('loan_Status_data/', views.LoanStatusData.as_view(), name='LoanStatusData'),
    path('loan_eligibility/', views.EligibilityCheck.as_view(), name='EligibilityCheck'),
    path('get_employee/', views.GetEmployeeDetails.as_view(), name='GetEmployeeDetails'),
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
    path('upload_image/', views.UploadImage.as_view(), name='image_upload'),
    path('user_details/', views.UserDetails.as_view(), name='userDetails'),
    path('user_details/<int:id>', views.UserDetailsWithId.as_view(), name='userDetails'),
    path('transaction_list/', views.TransactionList.as_view(), name='viewBalance'),
    path('transfer_money/', views.MoneyTransfer.as_view(), name='viewBalance'),
    path('view_balance/', views.ViewBalance.as_view(), name='ViewBalance'),
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