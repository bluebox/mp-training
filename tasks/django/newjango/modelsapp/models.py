from django.db import models

# Create your models here.


class CustomerTable(models.Model):

    customer_id = models.IntegerField(primary_key=True)
    customer_name = models.CharField(max_length=50)
    phone_number = models.CharField(max_length=100)
    customer_email = models.CharField(max_length=50)
    customer_password = models.IntegerField()
    customer_address = models.CharField(max_length=100)


class LoanDetails(models.Model):
    id = models.IntegerField(primary_key=True)
    loan_amount = models.CharField(max_length=100)
    date_of_approval = models.DateField()
    loan_type = models.CharField(max_length=100)
    customer = models.ForeignKey(CustomerTable, on_delete=models.CASCADE)


class AccountTable(models.Model):
    account_number = models.IntegerField()
    amount = models.IntegerField()
    account_type = models.CharField(max_length=100)
    customer = models.ForeignKey(CustomerTable, on_delete= models.CASCADE)


class DebitCard(models.Model):
    debit_id = models.IntegerField(primary_key=True)
    account_number = models.OneToOneField(AccountTable, on_delete=models.CASCADE)
    card_number = models.IntegerField()
    expire_date = models.DateField()
    cvv = models.IntegerField()


class BeneficiaryDetails(models.Model):
    benf_id = models.IntegerField()
    benf_name = models.CharField(max_length=100)
    benf_number = models.IntegerField()
    amount = models.IntegerField()
    customer = models.ForeignKey(CustomerTable, on_delete=models.CASCADE)



class CreditCard(models.Model):
    credit_id = models.IntegerField()
    card_number = models.IntegerField()
    expire_date = models.DateField(auto_now_add=True)
    cvv = models.IntegerField()
    card_amount = models.IntegerField()
    customer = models.OneToOneField(CustomerTable, on_delete=models.CASCADE)


class TransactionTable(models.Model):
    transaction_id = models.BigAutoField(primary_key=True)
    transaction_amount = models.IntegerField()
    sender = models.ForeignKey(CustomerTable, related_name="sender", on_delete=models.CASCADE)
    receiver = models.ForeignKey(CustomerTable, related_name="receiver", on_delete=models.CASCADE)


class Logs(models.Model):
    log_id = models.BigAutoField(primary_key=True)
    transaction_id = models.OneToOneField(TransactionTable, on_delete=models.CASCADE)
    date_of_transaction = models.DateField(auto_now=True)
    transaction_time = models.TimeField(auto_now=True)


class EmployesTable(models.Model):
    employee_id = models.IntegerField(primary_key=True)
    employee_name = models.CharField(max_length=50)
    emp_email = models.CharField(max_length=50)
    emp_password = models.IntegerField()
    employee_address = models.CharField(max_length=100)