import datetime
import json

from .jwtAthentication import create_access_token, create_refresh_token
from .models import CustomerTable, EmployesTable, EmployeeToken, AccountTable, CustomerToken, TransactionTable
from .serializers import ModSeri, EmpSerializer,EmployeeEmailAndPass
from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt

from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from django.http import JsonResponse

from django.contrib.auth.models import User

from rest_framework.views import APIView

from rest_framework.authentication import TokenAuthentication
from rest_framework.permissions import IsAuthenticated
from rest_framework.exceptions import APIException
from rest_framework.parsers import JSONParser
from rest_framework import mixins
from rest_framework import generics

from django.utils import timezone
import datetime

# print(timezone.now())  # The UTC time
# print(timezone.localtime())  # timezone specified time,if timezone is UTC, it is same as above
# print(datetime.datetime.now())  # default localmachine time


from rest_framework.authtoken.models import Token
# class GenericAPIView(generics.GenericAPIView, mixins.ListModelMixin, mixins.CreateModelMixin, mixins.UpdateModelMixin,
#                      mixins.RetrieveModelMixin, mixins.DestroyModelMixin):
#     serializer_class = UserSerializers
#     queryset = CustomerTable.objects.all()
#
#     lookup_field = 'first_name'
#     def get(self, request, first_name):
#         if first_name:
#             return self.retrieve(request)
#         else:
#             return self.list(request)
#
#     def post(self, request):
#         return self.create(request)
#
#     def put(self,request, first_name):
#         return self.update(request, first_name)
#
#     def delete(self,request, first_name):
#         return self.destroy(request, first_name)


class ClassBasedViews(APIView):

    def get(self, request):
        datafromdb = CustomerTable.objects.all()
        serializer = ModSeri(datafromdb, many= True)
        return Response(serializer.data)
    def post(self,request):
        serializer = ModSeri(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class ClassBasedViewsWithId(APIView):
    authentication_classes = [TokenAuthentication]
    permission_classes = [IsAuthenticated]
    def get_object(self, id):
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


    def post(self,request, id, format = None):
        serializer = ModSeri(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


    def put(self, request, id, format = None):
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

    def get(self, request):
        datafromdb = EmployesTable.objects.all()
        serializer = EmpSerializer(datafromdb, many= True)
        return Response(serializer.data)
    def post(self,request):
        serializer = EmpSerializer(data=request.data)
        # mails = EmployesTable.objects.filter(emp_email = request.data['emp_email'])
        if EmployesTable.objects.filter(emp_email = request.data['emp_email']).exists():
            return Response("This Email is already existed")
        else:
            if serializer.is_valid():
                serializer.save()
                user = EmployesTable.objects.get(emp_email =request.data['emp_email'])
                access_token = create_access_token(user.employee_id)
                refresh_token = create_refresh_token(user.employee_id)
                EmployeeToken.objects.create(
                        emp_id=user.employee_id,
                        token_key=refresh_token,
                        expiry_date=datetime.datetime.utcnow() + datetime.timedelta(days=7)
                    )
                response = Response()
    #
                response.set_cookie(key='refresh_token', value=refresh_token, httponly=False)
                response.data = {
                    'token': access_token,
                }
                return response
                # return Response(serializer.data, status=status.HTTP_201_CREATED)
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
            #     return Response([serializer.data, "Registration Successful"],status=status.HTTP_201_CREATED)
            # return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class EmpClassBasedViewsWithId(APIView):
    # authentication_classes = [TokenAuthentication]
    # permission_classes = [IsAuthenticated]
    def get_object(self, id):
        try:
            return EmployesTable.objects.filter(employee_id=id)
        except EmployesTable.DoesNotExist:
            return HttpResponse(status=status.HTTP_404_NOT_FOUND)

    def get(self, request, id , format = None):
        if id:
            data = self.get_object(id)
            serializer = EmpSerializer(data, many= True)
        else:
            data = EmployesTable.objects.all()
            serializer = EmpSerializer(data)
        return Response(serializer.data)


    def post(self,request, id, format = None):
        serializer = EmpSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


    def put(self, request, id, format = None):
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


# @csrf_exempt
# def emp_login_check(request):
#     if request.method == "POST":
#         res =json.loads(request.body.decode('utf-8'))
#         if EmployesTable.objects.filter(emp_email = res["emp_email"]).exists():
#             if EmployesTable.objects.filter(emp_email = res["emp_email"] ,emp_password=res["emp_password"]).exists():
#
#                 # user = EmployesTable.objects.get(emp_email = res["emp_email"], emp_password=res["emp_password"])
#                 # # serializer = EmployeeEmailAndPass(user)
#                 return JsonResponse(res, safe=False)
#             else:
#                 return JsonResponse({"emp_email":'', "emp_password":"Incorrect password"}, safe=False)
#
#         else:
#             return JsonResponse({"emp_email":'Email doesn\'t exist', "emp_password":""}, safe=False)
#


class Login(APIView):
    def post(self, request):
        email = request.data['emp_email']
        password = request.data['emp_password']
        if EmployesTable.objects.filter(emp_email= email).exists():
            if EmployesTable.objects.filter(emp_email = email,emp_password=password):

                user = EmployesTable.objects.get(emp_email= email, emp_password= password)

                access_token = create_access_token(user.emp_email)
                refresh_token = create_refresh_token(user.emp_email)

                EmployeeToken.objects.create(
                    emp_id=user.employee_id,
                    token_key=refresh_token,
                    expiry_date =datetime.datetime.utcnow() + datetime.timedelta(days=5)
                )
                response = Response()
                response.set_cookie(key='refresh_token', value=refresh_token, httponly=False)
                response.data = {
                    'access_token': access_token,
                    "refresh_token":refresh_token
                }
                return response

            else:
                # raise APIException('Wrong Password!')
                return Response({"emp_email":'', "emp_password":"Incorrect password"})

        else:
            # raise APIException("email doesn't exist, Try again")
            return Response({"emp_email":"Email doesn't exist", "emp_password":""})




class CustomerTokenGeneration(APIView):
    def post(self, request):
        account_serializer0= {}
        account_serializer1 = {}
        data = {}
        email = request.data['customer_email']
        password = request.data['customer_password']
        if CustomerTable.objects.filter(customer_email= email).exists():
            if CustomerTable.objects.filter(customer_email = email,customer_password=password):

                user = CustomerTable.objects.get(customer_email= email, customer_password= password)
                serializer = ModSeri(user)
                print(serializer.data["customer_id"])
                account= AccountTable.objects.filter(customer = serializer.data["customer_id"])
                if account.count() > 1:

                    account_serializer0 = EmployeeEmailAndPass(account[0])
                    print(account_serializer0.data)
                    account_serializer1 = EmployeeEmailAndPass(account[1])
                    print(account_serializer1.data)
                    if account_serializer0.data["account_type"] == "savings":
                        data = {
                            "savings_account": account_serializer0.data["account_number"],
                            "savings_account_data": account_serializer0.data,

                            "current_account": account_serializer1.data["account_number"],
                            "current_account_data": account_serializer1.data,
                        }
                    else:
                        data = {
                            "savings_account": account_serializer1.data["account_number"],
                            "savings_account_data": account_serializer1.data,
                            "current_account": account_serializer0.data["account_number"],
                            "current_account_data": account_serializer0.data,
                        }


                else:
                    # print(account[0]["account_type"])

                    account_serializer0 = EmployeeEmailAndPass(account[0])
                    print(account_serializer0.data)
                    if account_serializer0.data["account_type"] == "savings":

                        savings_account_number = account_serializer0.data["account_number"]
                        current_account_number = "No Account"
                        data = {
                            "savings_account": savings_account_number,
                            "current_account": current_account_number,
                            "account_data": account_serializer0.data,
                        }
                    else:
                        savings_account_number = "No Account"
                        current_account_number = account_serializer0.data["account_number"]
                        data = {
                            "savings_account": savings_account_number,
                            "current_account": current_account_number,
                            "account_data": account_serializer1.data,
                        }


                customer_access_token = create_access_token(user.customer_email)
                customer_refresh_token = create_refresh_token(user.customer_email)

                CustomerToken.objects.create(
                    customer_id=user.customer_id,
                    token_key=customer_refresh_token,
                    expiry_date =datetime.datetime.utcnow() + datetime.timedelta(days=5)
                )
                response = Response()
                response.set_cookie(key='customer_refresh_token', value=customer_refresh_token, httponly=False)
                response.data = {
                    'customer_access_token': customer_access_token,
                    "customer_refresh_token":customer_refresh_token,\
                    # "customer_data": serializer.data,
                    # "account_data": data


                }
                return response

            else:
                # raise APIException('Wrong Password!')
                return Response({"emp_email":'', "emp_password":"Incorrect password"})

        else:
            # raise APIException("email doesn't exist, Try again")
            return Response({"emp_email":"Email doesn't exist", "emp_password":""})


class CustomerAccCreate(APIView):
    def post(self, request):
        email = request.data['customer_email']
        if CustomerTable.objects.filter(customer_email= email).exists():
            user = CustomerTable.objects.get(customer_email= email)
            serialized = ModSeri(user)
            return Response(f"This email already exist, Please try another Email `Customer_id: {serialized.data['customer_id']}`")
        else:

            serializer = ModSeri(data=request.data)
            print(serializer)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data["customer_id"])
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class AddingAccount(APIView):
    def post(self, request):
        customer_id = request.data["customer"]
        acc_type = request.data["account_type"]
        if AccountTable.objects.filter(customer = customer_id).exists():
            user = AccountTable.objects.filter(customer = customer_id).values()
            if user.count() > 1:
                return Response("Both accounts are already existed")
            else:

                if user[0]["account_type"] == acc_type:
                    return Response("This account is already existed")
                else:
                    serializer = EmployeeEmailAndPass(data=request.data)
                    if serializer.is_valid():
                        serializer.save()
                        return Response("Successfully created")

                return Response("This email already exist, Please try another Email")
        else:
            serializer = EmployeeEmailAndPass(data=request.data)
            if serializer.is_valid():
                serializer.save()
                return Response("Successfully created")

class viewBalance(APIView):
    def post(self, request):
        acc_type = request.data["account_type"]
        email = request.data["email"]
        print(acc_type)
        user = CustomerTable.objects.get(customer_email = email)
        serializer = ModSeri(user)
        # print(serializer.data)

        if AccountTable.objects.filter(customer = serializer.data["customer_id"]).exists():
            print("yes")
            if AccountTable.objects.filter(account_type = acc_type, customer = serializer.data["customer_id"]).exists():
                account_data = AccountTable.objects.get(account_type=acc_type, customer=serializer.data["customer_id"])
                serialized_data = EmployeeEmailAndPass(account_data)
                print(serialized_data.data)
                return Response(serialized_data.data["amount"])
            else:
                return Response("Account doesn't exist")



class MoneyTransfer(APIView):
    def post(self, request):
        email = request.data["customer_email"]
        acc_num = request.data["account_number"]
        amount = request.data["amount"]
        name = request.data["receiver_name"]
        get_user = CustomerTable.objects.get(customer_email = email)
        get_user_serializer = ModSeri(get_user)
        user_id = get_user_serializer.data["customer_id"]
        if AccountTable.objects.filter(customer = user_id).exists():
            sender_account = AccountTable.objects.filter(customer = user_id)
            if sender_account.count() > 1:
                for i in sender_account:
                    if i.account_type == "savings":
                        sender_serializer = EmployeeEmailAndPass(i)
                        if AccountTable.objects.filter(account_number=acc_num).exists():
                            user_account_details = AccountTable.objects.get(account_number=acc_num)
                            receiver_serializer = EmployeeEmailAndPass(user_account_details)
                            user = CustomerTable.objects.get(customer_id = receiver_serializer.data["customer"])
                            user_serializer = ModSeri(user)
                            if user_serializer.data["customer_name"] == name:
                                if user_id != receiver_serializer.data["customer"]:
                                    if sender_serializer.data["amount"] > int(amount):
                                        remaining_amount = sender_serializer.data["amount"] - int(amount)
                                        adding_amount = receiver_serializer.data["amount"] + int(amount)
                                        string_amount = amount
                                        i.amount = remaining_amount
                                        i.save()
                                        user_account_details.amount = adding_amount
                                        user_account_details.save()
                                        transaction = TransactionTable(transaction_amount = string_amount, receiver_id = receiver_serializer.data["customer"], sender_id = user_id, receiver_status = "Credited", sender_status = "Debited", transaction_time = datetime.datetime.now())
                                        transaction.save()
                                        return Response("Transaction Successful")
                                    else:
                                        return Response("Insufficient Funds")
                                else:
                                    return Response("Cannot send to same users")
                            else:
                                return Response("Name doesn't matched")
                        else:
                            return Response("Enter Valid Account Number")
                    else:
                        continue
            else:
                sender_account = AccountTable.objects.get(customer=user_id)
                sender_serializer = EmployeeEmailAndPass(sender_account)

                if AccountTable.objects.filter(account_number=acc_num).exists():
                    user_account_details = AccountTable.objects.get(account_number=acc_num)
                    receiver_serializer = EmployeeEmailAndPass(user_account_details)
                    user = CustomerTable.objects.get(customer_id = receiver_serializer.data["customer"])
                    user_serializer = ModSeri(user)
                    if user_serializer.data["customer_name"] == name:
                        if int(user_id) != receiver_serializer.data["customer"]:
                            if sender_serializer.data["amount"] > int(amount):
                                remaining_amount = sender_serializer.data["amount"] - int(amount)
                                adding_amount = receiver_serializer.data["amount"] + int(amount)
                                string_amount = amount
                                sender_account.amount = remaining_amount
                                sender_account.save()
                                user_account_details.amount = adding_amount
                                user_account_details.save()
                                transaction = TransactionTable(transaction_amount=string_amount,
                                                               receiver_id=receiver_serializer.data["customer"],
                                                               sender_id=user_id, receiver_status="Credited",
                                                               sender_status="Debited",  transaction_time = datetime.datetime.now())
                                transaction.save()
                                return Response("Transaction Successful")
                            else:
                                return Response("Insufficient Funds")
                        else:
                            return Response("Cannot send to same users")
                    else:
                        return Response("Name doesn't matched")
                else:
                    return Response("Enter Valid Account Number")


class TransactionList(APIView):
    def get(self, request):
        list = TransactionTable.objects.all().values()
        return Response(list)


#     def post(self, request, format=None):
#         print(request.data)
#
#         serializer = UserSerializer(data=request.data)
#         if serializer.is_valid():
#             serializer.save()
#             user = User.objects.get(email=request.data['email'])
#             access_token = create_access_token(user.id)
#             refresh_token = create_refresh_token(user.id)
#             UserToken.objects.create(
#                 user_id=user.id,
#                 token=refresh_token,
#                 expired_at=datetime.datetime.utcnow() + datetime.timedelta(days=7)
#             )
#             response = Response()
#
#             response.set_cookie(key='refresh_token', value=refresh_token, httponly=False)
#             response.data = {
#                 'token': access_token,
#             }
#             return response
#             # return Response(serializer.data, status=status.HTTP_201_CREATED)
#         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
#
#
# class Login(APIView):
#     def post(self, request):
#         email = request.data['email']
#         password = request.data['password']
#         if User.objects.filter(email=email).exists():
#             user = User.objects.get(email=email)
#             if user.password != password:
#                 raise exceptions.APIException('Wrong Password!')
#
#         else:
#             raise exceptions.APIException("email doesn't exist, Try again")
#
#         user = User.objects.get(email=email, password=password)
#
#         access_token = create_access_token(user.id)
#         refresh_token = create_refresh_token(user.id)
#
#         UserToken.objects.create(
#             user_id=user.id,
#             token=refresh_token,
#             expired_at=datetime.datetime.utcnow() + datetime.timedelta(days=5)
#         )
#         response = Response()
#         response.set_cookie(key='refresh_token', value=refresh_token, httponly=False)
#         response.data = {
#             'access_token': access_token
#         }
#         return response
#
#
# class RefreshJwtTokenViewSet(APIView):
#
#     def post(self, request):
#         refresh_token = request.COOKIES.get('refresh_token')
#         user_id = decode_refresh_token(refresh_token)
#         if not UserToken.objects.filter(
#                 user_id=user_id,
#                 token=refresh_token,
#                 expired_at__gt=datetime.datetime.now(tz=datetime.timezone.utc)
#         ).exists():
#             raise exceptions.AuthenticationFailed('Unauthenticated')
#
#         access_token = create_access_token(user_id)
#
#         return Response({
#             'token': access_token
#         })
#
#
# class LogoutViewSet(APIView):
#
#     def post(self, request):
#         refresh_token = request.COOKIES.get('refresh_token')
#         UserToken.objects.filter(
#             token=refresh_token
#         ).delete()
#
#         response = Response()
#         response.delete_cookie(key='refresh_token')
#
#         response.data = {
#             'message': 'successfully logged out',
#         }
#
#         return response