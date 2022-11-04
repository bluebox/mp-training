from .models import CustomerTable, EmployesTable, EmployeeToken, AccountTable, CustomerToken, TransactionTable, \
    CustomerImageTable, DebitCard, CreditCard, CustomerRequests, TotalRequestsTable, CustomerLoanStatusTable, \
    LoanDetails
from .serializers import ModSeri, EmpSerializer, EmployeeEmailAndPass, ImageSerializer, TransactionSerializer, \
    DebiCardSerializer, CreditCardSerializer, RequestSerializer, LoanStatusSerializer, LoanSerializer

from rest_framework.response import Response
from .jwtAthentication import create_access_token, create_refresh_token
import datetime
from rest_framework import status
from rest_framework import exceptions
from django.db.models import Q


class Employee:
    @staticmethod
    def get_data(request):
        data_from_db = EmployesTable.objects.all()
        serializer = EmpSerializer(data_from_db, many=True)
        return serializer

    @staticmethod
    def employee_token_generation(request):
        serializer = EmpSerializer(data=request.data)
        if EmployesTable.objects.filter(emp_email=request.data['emp_email']).exists():
            return Response("This Email is already existed")
        else:
            if serializer.is_valid():
                serializer.save()
                user = EmployesTable.objects.get(emp_email=request.data['emp_email'])
                access_token = create_access_token(user.employee_id)
                refresh_token = create_refresh_token(user.employee_id)
                EmployeeToken.objects.create(
                    emp_id=user.employee_id,
                    token_key=refresh_token,
                    expiry_date=datetime.datetime.utcnow() + datetime.timedelta(days=7)
                )
                response = Response()

                response.set_cookie(key='refresh_token', value=refresh_token, httponly=False)
                response.data = {
                    'token': access_token,
                }
                return response
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    @staticmethod
    def employee_login(request):
        email = request.data['emp_email']
        password = request.data['emp_password']
        print(email)
        if EmployesTable.objects.filter(emp_email=email).exists():
            if EmployesTable.objects.filter(emp_email=email, emp_password=password):

                user = EmployesTable.objects.get(emp_email=email, emp_password=password)

                access_token = create_access_token(user.emp_email)
                refresh_token = create_refresh_token(user.emp_email)

                EmployeeToken.objects.create(
                    emp_id=user.employee_id,
                    token_key=refresh_token,
                    expiry_date=datetime.datetime.utcnow() + datetime.timedelta(days=5)
                )
                response = Response()
                response.set_cookie(key='refresh_token', value=refresh_token, httponly=False)
                response.data = {
                    'access_token': access_token,
                    "refresh_token": refresh_token
                }
                print(response)
                return response

            else:
                raise exceptions.APIException("Incorrect password")

        else:
            raise exceptions.APIException("Email doesn't exist", "emp_password")


class Customer:
    @staticmethod
    def customer_token_generation(request):
        account_serializer0 = {}
        account_serializer1 = {}
        data = {}
        email = request.data['customer_email']
        password = request.data['customer_password']
        if CustomerTable.objects.filter(customer_email=email).exists():
            if CustomerTable.objects.filter(customer_email=email, customer_password=password):

                user = CustomerTable.objects.get(customer_email=email, customer_password=password)
                serializer = ModSeri(user)
                account = AccountTable.objects.filter(customer=serializer.data["customer_id"])
                if account.count() > 1:

                    account_serializer0 = EmployeeEmailAndPass(account[0])
                    account_serializer1 = EmployeeEmailAndPass(account[1])
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

                    account_serializer0 = EmployeeEmailAndPass(account[0])
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
                    expiry_date=datetime.datetime.utcnow() + datetime.timedelta(days=5)
                )
                response = Response()
                response.set_cookie(key='customer_refresh_token', value=customer_refresh_token, httponly=False)
                response.data = {
                    'customer_access_token': customer_access_token,
                    "customer_refresh_token": customer_refresh_token,
                    "customer_data": serializer.data

                }
                return response

            else:
                raise exceptions.APIException("Incorrect password")

        else:
            raise exceptions.APIException("Email doesn't exist")

    @staticmethod
    def customer_account_create(request):
        email = request.data['customer_email']
        if CustomerTable.objects.filter(customer_email=email).exists():
            user = CustomerTable.objects.get(customer_email=email)
            serialized = ModSeri(user)
            return f"This email already exist, Please try another Email `Customer_id: {serialized.data['customer_id']}`"
        else:

            serializer = ModSeri(data=request.data)

            if serializer.is_valid():
                serializer.save()
                print(serializer.data)
                return serializer.data["customer_id"]
            else:
                raise exceptions.APIException("Invalid Credentials")

    @staticmethod
    def adding_customer_account(request):
        customer_id = request.data["customer"]
        acc_type = request.data["account_type"]
        if AccountTable.objects.filter(customer=customer_id).exists():
            user = AccountTable.objects.filter(customer=customer_id).values()
            if user.count() > 1:
                return "Both accounts are already existed"
            else:

                if user[0]["account_type"] == acc_type:
                    return "This account is already existed"
                else:
                    serializer = EmployeeEmailAndPass(data=request.data)
                    if serializer.is_valid():
                        serializer.save()
                        return "Successfully created"

                return "This email already exist, Please try another Email"
        else:
            serializer = EmployeeEmailAndPass(data=request.data)
            if serializer.is_valid():
                serializer.save()
                return "Successfully created"
            else:
                raise exceptions.APIException("Invalid Credentials")

    @staticmethod
    def customer_balance(request):
        acc_type = request.data["account_type"]
        email = request.data["email"]
        user = CustomerTable.objects.get(customer_email=email)
        serializer = ModSeri(user)

        if AccountTable.objects.filter(customer=serializer.data["customer_id"]).exists():
            if AccountTable.objects.filter(account_type=acc_type, customer=serializer.data["customer_id"]).exists():
                account_data = AccountTable.objects.get(account_type=acc_type, customer=serializer.data["customer_id"])
                serialized_data = EmployeeEmailAndPass(account_data)
                print(serialized_data.data)
                return serialized_data.data["amount"]
            else:
                raise exceptions.APIException("Account doesn't exist")
        else:
            raise exceptions.APIException("customer doesn't exist")

    @staticmethod
    def money_transfer(request):
        email = request.data["customer_email"]
        acc_num = request.data["account_number"]
        amount = request.data["amount"]
        name = request.data["receiver_name"]
        get_user = CustomerTable.objects.get(customer_email=email)
        get_user_serializer = ModSeri(get_user)
        user_id = get_user_serializer.data["customer_id"]
        if AccountTable.objects.filter(customer=user_id).exists():
            sender_account = AccountTable.objects.filter(customer=user_id)
            if sender_account.count() > 1:
                for i in sender_account:
                    if i.account_type == "savings":
                        sender_serializer = EmployeeEmailAndPass(i)
                        if AccountTable.objects.filter(account_number=acc_num).exists():
                            user_account_details = AccountTable.objects.get(account_number=acc_num)
                            receiver_serializer = EmployeeEmailAndPass(user_account_details)
                            user = CustomerTable.objects.get(customer_id=receiver_serializer.data["customer"])
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
                                        transaction = TransactionTable(transaction_amount=string_amount,
                                                                       receiver_id=receiver_serializer.data["customer"],
                                                                       sender_id=user_id, receiver_status="Credited",
                                                                       sender_status="Debited",
                                                                       transaction_time=datetime.datetime.now())
                                        transaction.save()
                                        return "Transaction Successful"
                                    else:
                                        raise exceptions.APIException("Insufficient Funds")
                                else:
                                    raise exceptions.APIException("Cannot send to same users")
                            else:
                                raise exceptions.APIException("Name doesn't matched")
                        else:
                            raise exceptions.APIException("Enter Valid Account Number")
                    else:
                        continue
            else:
                sender_account = AccountTable.objects.get(customer=user_id)
                sender_serializer = EmployeeEmailAndPass(sender_account)

                if AccountTable.objects.filter(account_number=acc_num).exists():
                    user_account_details = AccountTable.objects.get(account_number=acc_num)
                    receiver_serializer = EmployeeEmailAndPass(user_account_details)
                    user = CustomerTable.objects.get(customer_id=receiver_serializer.data["customer"])
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
                                                               sender_status="Debited",
                                                               transaction_time=datetime.datetime.now())
                                transaction.save()
                                return "Transaction Successful"
                            else:
                                raise exceptions.APIException("Insufficient Funds")
                        else:
                            raise exceptions.APIException("Cannot send to same users")
                    else:
                        raise exceptions.APIException("Name doesn't matched")
                else:
                    raise exceptions.APIException("Enter Valid Account Number")


    @staticmethod
    def transaction_list(request):
        email = request.data["email"]
        if CustomerTable.objects.filter(customer_email=email).exists():
            customer_data = CustomerTable.objects.get(customer_email=email)
            serialized = ModSeri(customer_data)
            id = serialized.data["customer_id"]
            if TransactionTable.objects.filter(Q(sender_id=id) | Q(receiver_id=id)).exists():
                user_transactions = TransactionTable.objects.filter(Q(sender_id=id) | Q(receiver_id=id))
                # print(user_transactions)
                data_list = []
                for i in user_transactions:
                    serialized = TransactionSerializer(i)
                    data_list.append(serialized.data)
                    # print(data_list)
                return data_list

    @staticmethod
    def user_dashboard_details(request):
        email = request.data["customer_email"]
        if CustomerTable.objects.filter(customer_email=email).exists():
            user = CustomerTable.objects.get(customer_email=email)
            serializer = ModSeri(user)
            customer_id = serializer.data["customer_id"]
            if CustomerImageTable.objects.filter(user_id=customer_id).exists():
                img_data = CustomerImageTable.objects.get(user_id=customer_id)
                img_serializer = ImageSerializer(img_data)
                data = {
                    "user_data": serializer.data,
                    "user_img": img_serializer.data
                }
                return data
            else:

                data = {
                    "user_data": serializer.data,
                    "user_img": {
                        "profile_image": "https://res.cloudinary.com/dad6lhibn/image/upload/v1665497330/avatardefault_92824_gzlbue.png"}

                }
                return data

    @staticmethod
    def image_upload(request):
        email = request.data["customer_email"]
        image_link = request.data["userLink"]
        if CustomerTable.objects.filter(customer_email=email).exists():
            user = CustomerTable.objects.get(customer_email=email)
            serializer = ModSeri(user)
            customer_id = serializer.data["customer_id"]
            if CustomerImageTable.objects.filter(user_id=customer_id).exists():
                img = CustomerImageTable.objects.get(user_id=customer_id)
                # img_serializer = ImageSerializer(img)
                img.profile_image = image_link
                img.save()
                return "Successfully Changed"
            else:
                img = CustomerImageTable(profile_image=image_link, cover_image="", user_id=customer_id)
                img.save()
                return "Successfully Uploaded"

    @staticmethod
    def get_cards(request):
        email = request.data["email"]
        if CustomerTable.objects.filter(customer_email=email).exists():
            customer_data = CustomerTable.objects.get(customer_email=email)
            serialized = ModSeri(customer_data)
            id = serialized.data["customer_id"]
            name = serialized.data["customer_name"]
            if AccountTable.objects.filter(customer=id, account_type="savings").exists():
                account_data = AccountTable.objects.get(customer=id, account_type="savings")
                serialized = EmployeeEmailAndPass(account_data)
                account_id = serialized.data["id"]
                if DebitCard.objects.filter(account_number_id=account_id).exists():
                    card_data = DebitCard.objects.get(account_number_id=account_id)
                    serialized_debit_data = DebiCardSerializer(card_data)
                    print("yes")
                    if CreditCard.objects.filter(customer_id=id).exists():
                        credit = CreditCard.objects.filter(customer_id=id)
                        serialized_credit = CreditCardSerializer(credit, many=True)

                        obj = {"debit_card": serialized_debit_data.data, "credit_card": serialized_credit.data[0],
                               "name": name}
                        return obj
                    else:
                        obj = {"debit_card": serialized_debit_data.data, "credit_card": {"status": "No Credit Card"},
                               "name": name}
                        return obj
                else:

                    obj = {"debit_card": {"status": "No Debit Card"}, "credit_card": {"status": "No Credit Card"}}
                    return obj

    @staticmethod
    def customer_request_views(request):
        email = request.data["email"]["email"]
        request_type = request.data["request_type"]
        if CustomerTable.objects.filter(customer_email=email).exists():
            customer_data = CustomerTable.objects.get(customer_email=email)
            serialized = ModSeri(customer_data)
            name = serialized.data["customer_name"]
            if TotalRequestsTable.objects.filter(customer_email=email, request_type=request_type).exists():
                raise exceptions.APIException("Already you have a card")
            else:

                request_table = CustomerRequests(customer_name=name, customer_email=email, request_type=request_type)
                request_table.save()

                total_table = TotalRequestsTable(customer_name=name, customer_email=email, request_type=request_type)
                total_table.save()

                data = CustomerRequests.objects.filter(customer_email=email)
                data_list = []
                for i in data:
                    serialized = RequestSerializer(i)
                    data_list.append(serialized.data)
                return data_list
        else:
            raise exceptions.APIException("email doesn't exists")


    @staticmethod
    def delete_request(request):
        id = request.data["id"]
        request_type = request.data["request_type"]
        email_id = request.data["customer_email"]

        if CustomerRequests.objects.filter(id=id).exists():
            remove = CustomerRequests.objects.get(id=id)
            remove.delete()
            if request_type == "Email Updation" or request_type == "Phone Number Updation":
                total_req = TotalRequestsTable.objects.filter(customer_email=email_id, request_type=request_type)
                total_req.delete()

            return "Deleted Successfully"
        else:
            raise exceptions.APIException("No id")

    @staticmethod
    def get_account_id(request):
        email = request.data["email"]
        if CustomerTable.objects.filter(customer_email=email).exists():
            customer_data = CustomerTable.objects.get(customer_email=email)
            serialized = ModSeri(customer_data)
            id = serialized.data["customer_id"]
            if AccountTable.objects.filter(customer=id, account_type="savings").exists():
                account_data = AccountTable.objects.get(customer=id, account_type="savings")
                serialized = EmployeeEmailAndPass(account_data)
                account_id = serialized.data["id"]
                return account_id
            else:
                raise exceptions.APIException("No account")

        else:
            raise exceptions.APIException("No mail")

    @staticmethod
    def create_credit_card(request):
        email = request.data["customer_email"]
        card_number = request.data["card_number"]
        card_amount = request.data["card_amount"]
        cvv = request.data["cvv"]
        expiry_date = request.data["expire_date"]
        if CustomerTable.objects.filter(customer_email=email).exists():
            customer_data = CustomerTable.objects.get(customer_email=email)
            serialized = ModSeri(customer_data)
            id = serialized.data["customer_id"]
            name = serialized.data["customer_name"]
            if CreditCard.objects.filter(customer_id=id).exists():
                raise exceptions.APIException("Card already generated")
            else:
                credit = CreditCard(card_number=card_number, card_amount=card_amount, cvv=cvv, expire_date=expiry_date,
                                    customer_id=id)
                credit.save()
                cus_request = CustomerRequests.objects.get(customer_email=email, request_type="Credit Card")
                cus_request.request_status = True
                cus_request.save()
                total_table = TotalRequestsTable(customer_name=name, customer_email=email, request_type="Credit Card")
                total_table.save()
                return "Created"

    @staticmethod
    def create_debit_card(request):
        email = request.data["customer_email"]
        card_number = request.data["card_number"]
        cvv = request.data["cvv"]
        expiry_date = request.data["expire_date"]
        if CustomerTable.objects.filter(customer_email=email).exists():
            customer_data = CustomerTable.objects.get(customer_email=email)
            serialized = ModSeri(customer_data)
            id = serialized.data["customer_id"]
            name = serialized.data["customer_name"]
            print(id)
            if AccountTable.objects.filter(customer=id, account_type="savings"):
                account = AccountTable.objects.get(customer=id, account_type="savings")
                serialized_account = EmployeeEmailAndPass(account)
                account_id = serialized_account.data["id"]
                if DebitCard.objects.filter(account_number_id=account_id).exists():

                    raise exceptions.APIException("Already you have debit card")
                else:
                    debit = DebitCard(card_number=card_number, cvv=cvv, expire_date=expiry_date,
                                      account_number_id=account_id)
                    debit.save()
                    cus_request = CustomerRequests.objects.get(customer_email=email, request_type="Debit Card")
                    cus_request.request_status = True
                    cus_request.save()
                    total_table = TotalRequestsTable(customer_name=name, customer_email=email,
                                                     request_type="Debit Card")
                    total_table.save()
                    return "Created"

            else:
                raise exceptions.APIException("no customer")

    @staticmethod
    def eligibility_check(request):
        email_id = request.data["email"]
        if CustomerTable.objects.filter(customer_email=email_id).exists():
            customer_data = CustomerTable.objects.get(customer_email=email_id)
            serialized = ModSeri(customer_data)
            id = serialized.data["customer_id"]
            salary = serialized.data["avg_monthly_salary"]
            if salary > 20000:
                loan_amount = round((salary * 0.15) * 60)
                amount_with_interest = loan_amount + (loan_amount * 0.11)
                monthly_emi = round(amount_with_interest / 60)
                loan_status_table = CustomerLoanStatusTable(loan_amount=loan_amount, rate_of_interest_in_percent=11,
                                                            monthly_EMI=monthly_emi, customer_id=id,
                                                            tenure_in_months=60)
                loan_status_table.save()
                return "User Eligible For Loan"
            else:
                cus_request = CustomerRequests.objects.get(customer_email=email_id, request_type="Personal Loan")
                cus_request.delete()
                total_table = TotalRequestsTable.objects.get(customer_email=email_id, request_type="Personal Loan")
                total_table.delete()

                return "User Not Eligible For Loan"

    @staticmethod
    def loan_status_data(request):
        email_id = request.data["email"]
        if CustomerTable.objects.filter(customer_email=email_id).exists():
            customer_data = CustomerTable.objects.get(customer_email=email_id)
            serialized = ModSeri(customer_data)
            id = serialized.data["customer_id"]
            if CustomerLoanStatusTable.objects.filter(customer=id).exists():
                loan_status = CustomerLoanStatusTable.objects.get(customer=id)
                serialized = LoanStatusSerializer(loan_status)
                return serialized.data
            else:
                raise exceptions.APIException("No Active Loans")

    @staticmethod
    def accept_loan(request):
        id = request.data["customer"]
        values = LoanDetails.objects.all().values()
        length = len(values)

        if CustomerTable.objects.filter(customer_id=id).exists():
            customer_data = CustomerTable.objects.get(customer_id=id)
            serialized = ModSeri(customer_data)
            email = serialized.data["customer_email"]
            name = serialized.data["customer_name"]
            loan_table = LoanDetails(id=length + 1, loan_amount=request.data["loan_amount"], loan_type="Personal Loan",
                                     customer_id=id, monthly_EMI=request.data["monthly_EMI"],
                                     tenure=request.data["tenure_in_months"], date_of_approval=datetime.datetime.now())
            loan_table.save()

            loan = CustomerLoanStatusTable.objects.get(customer=id)
            loan.loan_status = True
            loan.save()

            cus_request = CustomerRequests.objects.get(customer_email=email, request_type="Personal Loan")
            cus_request.request_status = True
            cus_request.save()
            total_table = TotalRequestsTable(customer_name=name, customer_email=email, request_type="Personal Loan")
            total_table.save()

            return "Loan Sanctioned"
        else:
            raise exceptions.APIException("customer doesn't exist")


    @staticmethod
    def loan_details_data(request):
        email_id = request.data["email"]
        if CustomerTable.objects.filter(customer_email=email_id).exists():
            customer_data = CustomerTable.objects.get(customer_email=email_id)
            serialized = ModSeri(customer_data)
            id = serialized.data["customer_id"]
            print(id)
            if LoanDetails.objects.filter(customer_id=id).exists():
                data = LoanDetails.objects.get(customer_id=id)
                serialized = LoanSerializer(data)

                return serialized.data
            else:
                raise exceptions.APIException("No Data")


    @staticmethod
    def reject_loan(request):
        id = request.data["customer"]
        if CustomerTable.objects.filter(customer_id=id).exists():
            customer_data = CustomerTable.objects.get(customer_id=id)
            serialized = ModSeri(customer_data)
            email = serialized.data["customer_email"]
            if CustomerRequests.objects.filter(customer_email=email, request_type="Personal Loan").exists():
                req = CustomerRequests.objects.filter(customer_email=email, request_type="Personal Loan")
                req.delete()
                total_req = TotalRequestsTable.objects.filter(customer_email=email, request_type="Personal Loan")
                total_req.delete()

                loan_status = CustomerLoanStatusTable.objects.filter(customer_id=id)
                loan_status.delete()

                return "Successfully Deleted"
            else:
                raise exceptions.APIException("Request Not Existed")

    @staticmethod
    def change_phone_number(request):
        email_id = request.data["customer_email"]
        old_number = request.data["old_phone_number"]
        new_number = request.data["new_number"]
        if CustomerTable.objects.filter(customer_email=email_id, phone_number=old_number).exists():
            customer_data = CustomerTable.objects.get(customer_email=email_id, phone_number=old_number)
            customer_data.phone_number = new_number
            customer_data.save()
            cus_request = CustomerRequests.objects.get(customer_email=email_id, request_type="Phone Number Updation")
            cus_request.request_status = True
            cus_request.save()

            total_req = TotalRequestsTable.objects.filter(customer_email=email_id, request_type="Phone Number Updation")
            total_req.delete()

            return "Successfully Updated"
        else:
            raise exceptions.APIException("Once check email phone number")


    @staticmethod
    def change_email(request):
        email = request.data["customer_email"]
        new_email = request.data["new_email"]
        if CustomerTable.objects.filter(customer_email=email).exists():
            customer_data = CustomerTable.objects.get(customer_email=email)
            customer_data.customer_email = new_email
            customer_data.save()
            cus_request = CustomerRequests.objects.get(customer_email=email, request_type="Email Updation")
            cus_request.request_status = True
            cus_request.save()

            total_req = TotalRequestsTable.objects.filter(customer_email=email, request_type="Email Updation")
            total_req.delete()

            return "Successfully Updated"
        else:
            raise exceptions.APIException("Once check email")




















