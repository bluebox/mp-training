
from .models import CustomerTable, EmployesTable, AccountTable, CustomerRequests,\
    LoanDetails
from .serializers import ModSeri, EmpSerializer, RequestSerializer, \
    CustomerTotalListSerializer, LoansSerializer

from .managers import Employee, Customer
from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework import status
from django.http import JsonResponse
from rest_framework.views import APIView
from rest_framework.authentication import TokenAuthentication
from rest_framework.permissions import IsAuthenticated
import datetime
from rest_framework import exceptions
from cloudinary.uploader import upload


class EmpClassBasedViewsWithId(APIView):
    @staticmethod
    def get_object(id):
        try:
            return EmployesTable.objects.filter(employee_id=id)
        except EmployesTable.DoesNotExist:
            return HttpResponse(status=status.HTTP_404_NOT_FOUND)

    def get(self, request, id ):
        if id:
            data = self.get_object(id)
            serializer = EmpSerializer(data, many= True)
        else:
            data = EmployesTable.objects.all()
            serializer = EmpSerializer(data)
        return Response(serializer.data)

    @staticmethod
    def post(request, id):
        serializer = EmpSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, id):
        user_data = self.get_object(id)
        serializer = EmpSerializer(user_data, data = request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id):
        user_data = self.get_object(id)
        user_data.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class Login(APIView):
    @staticmethod
    def post(request):
        try:
            login = Employee.employee_login(request)
            return login
        except Exception as e:
            return Response(str(e))


class CustomerTokenGeneration(APIView):
    @staticmethod
    def post(request):
        try:
            customer_token = Customer.customer_token_generation(request)
            return customer_token
        except Exception as e:
            return Response(str(e))


class CustomerAccCreate(APIView):
    @staticmethod
    def post(request):
        try:
            user_account = Customer.customer_account_create(request)
            return Response(user_account)
        except Exception as e:
            return Response(str(e))


class AddingAccount(APIView):
    @staticmethod
    def post(request):
        try:
            bank_account = Customer.adding_customer_account(request)
            return Response(bank_account)
        except Exception as e:
            return Response(str(e))


class ViewBalance(APIView):
    @staticmethod
    def post(request):
        try:
            balance = Customer.customer_balance(request)
            return Response(balance)
        except Exception as e:
            return Response(str(e))


class MoneyTransfer(APIView):
    @staticmethod
    def post(request):
        try:
            money_transfer = Customer.money_transfer(request)
            return Response(money_transfer)
        except Exception as e:
            return Response(str(e))


class TransactionList(APIView):
    @staticmethod
    def post(request):
        try:
            transaction_list = Customer.transaction_list(request)
            return Response(transaction_list)
        except Exception as e:
            return Response(str(e))


class UserDetailsWithId(APIView):
    @staticmethod
    def get(request, id):
        print(id)
        if CustomerTable.objects.filter(customer_id = id).exists():
            user = CustomerTable.objects.get(customer_id = id)
            serializer = ModSeri(user)
            return Response(serializer.data)


class UserDetails(APIView):
    @staticmethod
    def post(request):
        try:
            user_details = Customer.user_dashboard_details(request)
            return Response(user_details)
        except Exception as e:
            return Response(str(e))


class UploadImage(APIView):
    @staticmethod
    def post(request):
        payload = upload(request.data['image'])
        return JsonResponse({
            'imageUrl': payload['secure_url']
        })


class ImageUpload(APIView):
    @staticmethod
    def post(request):
        try:
            image_upload = Customer.image_upload(request)
            return Response(image_upload)
        except Exception as e:
            return Response(str(e))


class GetCards(APIView):
    @staticmethod
    def post(request):
        try:
            cards = Customer.get_cards(request)
            return Response(cards)
        except Exception as e:
            return Response(str(e))


class CustomerRequestsView(APIView):
    @staticmethod
    def post(request):
        try:
            customer_requests = Customer.customer_request_views(request)
            return Response(customer_requests)
        except Exception as e:
            return Response(str(e))


class RequestData(APIView):
    @staticmethod
    def post(request):
        email = request.data["email"]
        if CustomerRequests.objects.filter(customer_email = email).exists():
            data = CustomerRequests.objects.filter(customer_email = email)

            data_list = []
            for i in data:
                serialized = RequestSerializer(i)
                data_list.append(serialized.data)
                # print(data_list)
            return Response(data_list)
        else:
            return Response("Nothing")


class EmployeeRequestData(APIView):
    @staticmethod
    def post(request):
        email = request.data["email"]
        if EmployesTable.objects.filter(emp_email = email).exists():
            data = CustomerRequests.objects.all().values()
            return Response(data)
        else:
            return Response("You don't have access")


class DeleteRequest(APIView):
    @staticmethod
    def post(request):
        try:
            delete_request = Customer.delete_request(request)
            return Response(delete_request)
        except Exception as e:
            return Response(str(e))


class GetAccountId(APIView):
    @staticmethod
    def post(request):
        try:
            account_id = Customer.get_account_id(request)
            return Response(account_id)
        except Exception as e:
            return Response(str(e))


class CreateCreditCard(APIView):
    @staticmethod
    def post(request):
        try:
            credit_card = Customer.create_credit_card(request)
            return Response(credit_card)
        except Exception as e:
            return Response(str(e))


class CreateDebitCard(APIView):
    @staticmethod
    def post(request):
        try:
            debit_card = Customer.create_debit_card(request)
            return Response(debit_card)
        except Exception as e:
            return Response(str(e))


class NumberOfRequests(APIView):
    @staticmethod
    def get(request):
        requests = CustomerRequests.objects.filter(request_status = False)
        length = len(requests)
        return Response(length)


class GetEmployeeDetails(APIView):
    @staticmethod
    def post(request):
        email = request.data["email"]
        emp_data = EmployesTable.objects.get(emp_email = email)
        serialized =EmpSerializer(emp_data)

        return Response(serialized.data["employee_name"])


class EligibilityCheck(APIView):
    @staticmethod
    def post(request):
        try:
            checking = Customer.eligibility_check(request)
            return Response(checking)
        except Exception as e:
            return Response(str(e))


class LoanStatusData(APIView):
    @staticmethod
    def post(request):
        try:
            loan_status = Customer.loan_status_data(request)
            return Response(loan_status)
        except Exception as e:
            return Response(str(e))


class AcceptLoan(APIView):
    @staticmethod
    def post(request):
        try:
            accept_loan = Customer.accept_loan(request)
            return Response(accept_loan)
        except Exception as e:
            return Response(str(e))


class LoanDetailsData(APIView):
    @staticmethod
    def post(request):
        try:
            loan_details = Customer.loan_details_data(request)
            return Response(loan_details)
        except Exception as e:
            return Response(str(e))


class RejectLoan(APIView):
    @staticmethod
    def post(request):
        try:
            loan_rejection = Customer.reject_loan(request)
            return Response(loan_rejection)
        except Exception as e:
            return Response(str(e))


class CustomerList(APIView):
    @staticmethod
    def get(request):
        account = AccountTable.objects.all()
        serializer = CustomerTotalListSerializer(account, many = True)
        return Response(serializer.data )


class CustomerCount(APIView):
    @staticmethod
    def get(request):
        count = CustomerTable.objects.all().values()
        return Response(len(count) )


class LoansListInEmployeeDashBoard(APIView):
    @staticmethod
    def get(request):
        loans = LoanDetails.objects.all()
        serialized = LoansSerializer(loans, many = True)
        return Response(serialized.data)


class ChangePhoneNumber(APIView):
    @staticmethod
    def post(request):
        try:
            res = Customer.change_phone_number(request)
            return Response(res)
        except Exception as e:
            return Response(str(e))


class ChangeEmail(APIView):
    @staticmethod
    def post(request):
        try:
            res = Customer.change_email(request)
            return Response(res)
        except Exception as e:
            return Response(str(e))


class GetDataBySearch(APIView):
    @staticmethod
    def post(request):
        name  = request.data["text"]
        account = AccountTable.objects.filter(customer__customer_name__icontains = name)
        serializer = CustomerTotalListSerializer(account, many=True)
        return Response(serializer.data)


class GetDataBySearchLoans(APIView):
    @staticmethod
    def post(request):
        name  = request.data["text"]
        print(name)
        account = LoanDetails.objects.filter(customer__customer_name__icontains = name)
        serializer = LoansSerializer(account, many = True)
        return Response(serializer.data)


class ClassBasedViews(APIView):
    @staticmethod
    def get(request):
        datafromdb = CustomerTable.objects.all()
        serializer = ModSeri(datafromdb, many= True)
        return Response(serializer.data)

    def post(self, request):
        serializer = ModSeri(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class ClassBasedViewsWithId(APIView):
    authentication_classes = [TokenAuthentication]
    permission_classes = [IsAuthenticated]

    @staticmethod
    def get_object(id):
        try:
            return CustomerTable.objects.filter(customer_id=id)
        except CustomerTable.DoesNotExist:
            return HttpResponse(status=status.HTTP_404_NOT_FOUND)

    def get(self, request, id , format = None):
        if id:
            data = self.get_object(id)
            serializer = ModSeri(data, many= True)
        else:
            data = CustomerTable.objects.all()
            serializer = ModSeri(data)
        return Response(serializer.data)

    @staticmethod
    def post(request, id):
        serializer = ModSeri(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, id):
        user_data = self.get_object(id)
        serializer = ModSeri(user_data, data = request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id):
        user_data = self.get_object(id)
        user_data.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class EmpClassBasedViews(APIView):
    @staticmethod
    def get(request):

        data_from_db = Employee.get_data(request)
        return Response(data_from_db.data)

    @staticmethod
    def post(request):
        data = Employee.employee_token_generation(request)
        return Response(data)











