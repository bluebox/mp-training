from django.db import models

# Create your models here.


class CustomerTable(models.Model):

    customer_id = models.BigAutoField(primary_key=True)
    customer_name = models.CharField(max_length=50)
    phone_number = models.CharField(max_length=100)
    customer_email = models.CharField(max_length=50)
    customer_password = models.IntegerField()
    customer_address = models.CharField(max_length=100)
    employee_type = models.CharField(max_length=100, null = True)
    avg_monthly_salary = models.BigIntegerField(null = True)


class LoanDetails(models.Model):
    id = models.BigAutoField(primary_key=True)
    loan_amount = models.CharField(max_length=100)
    date_of_approval = models.DateTimeField(auto_now_add=True,null = True)
    loan_type = models.CharField(max_length=100)
    customer = models.ForeignKey(CustomerTable, on_delete=models.CASCADE)
    tenure = models.IntegerField(blank=True, null=True)
    monthly_EMI = models.IntegerField(blank=True, null = True)


class AccountTable(models.Model):
    account_number = models.BigIntegerField()
    amount = models.IntegerField()
    account_type = models.CharField(max_length=100)
    customer = models.ForeignKey(CustomerTable, on_delete= models.CASCADE)


class DebitCard(models.Model):
    # debit_id = models.IntegerField(primary_key=True)
    account_number = models.OneToOneField(AccountTable, on_delete=models.CASCADE)
    card_number = models.CharField(max_length=100)
    expire_date = models.CharField(max_length=100)
    cvv = models.CharField(max_length=200)


class BeneficiaryDetails(models.Model):
    benf_id = models.IntegerField()
    benf_name = models.CharField(max_length=100)
    benf_number = models.IntegerField()
    amount = models.IntegerField()
    customer = models.ForeignKey(CustomerTable, on_delete=models.CASCADE)



class CreditCard(models.Model):
    card_number = models.CharField(max_length=100, null = True)
    expire_date = models.CharField(max_length=100)
    cvv = models.IntegerField()
    card_amount = models.BigIntegerField()
    customer = models.OneToOneField(CustomerTable, on_delete=models.CASCADE)


class TransactionTable(models.Model):
    transaction_id = models.BigAutoField(primary_key=True)
    transaction_amount = models.CharField(max_length=50)
    sender = models.ForeignKey(CustomerTable, related_name="sender", on_delete=models.CASCADE)
    receiver = models.ForeignKey(CustomerTable, related_name="receiver", on_delete=models.CASCADE)
    sender_status = models.CharField(max_length=200, null = True)
    receiver_status = models.CharField(max_length=200 ,null= True)
    transaction_time = models.DateTimeField( auto_now_add= True,null = True)


# class Logs(models.Model):
#     log_id = models.BigAutoField(primary_key=True)
#     transaction = models.OneToOneField(TransactionTable, on_delete=models.CASCADE)
#     # date_of_transaction = models.DateField(auto_now=True)



class EmployesTable(models.Model):
    employee_id = models.BigAutoField(primary_key=True)
    employee_name = models.CharField(max_length=50)
    emp_email = models.CharField(max_length=50)
    emp_password = models.IntegerField()
    employee_address = models.CharField(max_length=100)


class EmployeeToken(models.Model):
    token = models.BigAutoField(primary_key=True)
    emp_id = models.IntegerField()
    token_key = models.CharField(max_length=200)
    expiry_date = models.DateTimeField(auto_now=True)



class CustomerToken(models.Model):
    token = models.BigAutoField(primary_key=True)
    customer_id = models.IntegerField()
    token_key = models.CharField(max_length=200)
    expiry_date = models.DateTimeField(auto_now=True)


class CustomerImageTable(models.Model):
    profile_image = models.CharField(max_length=1000)
    cover_image = models.CharField(max_length=1000)
    user_id = models.IntegerField()


class CustomerRequests(models.Model):
    customer_name = models.CharField(max_length=200)
    customer_email = models.CharField(max_length=200)
    request_type = models.CharField(max_length=200)
    request_status = models.BooleanField(default=False)



class TotalRequestsTable(models.Model):
    customer_name = models.CharField(max_length=200)
    customer_email = models.CharField(max_length=200)
    request_type = models.CharField(max_length=200)



class CustomerLoanStatusTable(models.Model):
    id = models.BigAutoField(primary_key=True)
    loan_amount = models.BigIntegerField()
    rate_of_interest_in_percent= models.IntegerField()
    tenure_in_months = models.IntegerField()
    monthly_EMI = models.BigIntegerField()
    loan_status = models.BooleanField(default=False)
    customer = models.ForeignKey(CustomerTable, on_delete=models.CASCADE)