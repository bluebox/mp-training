from django.db import models

# Create your models here.
class Admin(models.Model):
    Admin_id = models.CharField(max_length=50,primary_key=True)
    Admin_first_name = models.CharField(max_length=50)
    Admin_last_name = models.CharField(max_length=50)
    Admin_email_id = models.CharField(max_length=50)
    Admin_contact_number = models.IntegerField()
    Admin_password = models.CharField(max_length=50)
    
class Customer(models.Model):
    Customer_id = models.CharField(max_length=50,primary_key=True)
    Customer_first_name = models.CharField(max_length=50)
    Customer_last_name = models.CharField(max_length=50)
    Customer_Email_id = models.CharField(max_length=50)
    Customer_contact_number = models.IntegerField()
    Customer_password = models.CharField(max_length=50)

class Movie(models.Model):
    Movie_name = models.CharField(max_length=50,primary_key=True)
    Amount_paid = models.IntegerField()
    start_date = models.DateField()
    end_date = models.DateField()
    Ticket_price = models.IntegerField()

class Hall(models.Model):
    Hall_number = models.IntegerField(primary_key=True)
    Movie_name = models.ForeignKey(Movie, on_delete=models.CASCADE)
    Capacity = models.IntegerField()

class Reservation(models.Model):
    res_id = models.CharField(max_length=50,primary_key=True)
    number_of_tickets = models.IntegerField()
    Customer_id = models.ForeignKey(Customer, on_delete=models.CASCADE)
    Hall_number = models.ForeignKey(Hall, on_delete=models.CASCADE)
    res_date = models.DateField(max_length=50)
    show_time = models.TimeField(max_length=50)

class Transaction(models.Model):
    trans_id = models.CharField(max_length=50,primary_key=True)
    res_id = models.ForeignKey(Reservation, on_delete=models.CASCADE)
    trans_date = models.DateField()
    Admin_id = models.ForeignKey(Admin, on_delete=models.CASCADE)
    trans_type = models.CharField(max_length=50)
    Total_amount = models.IntegerField()

class Ticket(models.Model):
    Ticket_id = models.CharField(max_length=50,primary_key=True)
    trans_id = models.ForeignKey(Transaction, on_delete=models.CASCADE)
    res_id = models.ForeignKey(Reservation, on_delete=models.CASCADE)

class Seating_arrangement(models.Model):
    seat_number = models.IntegerField(primary_key=True)
    series = models.CharField(max_length=50)
    ticket_id = models.ForeignKey(Ticket, on_delete=models.CASCADE)

class Report(models.Model):
    Report_id = models.CharField(max_length=50,primary_key=True)
    report_date = models.DateField(max_length=50)
    Hall_number =models.ForeignKey(Hall, on_delete=models.CASCADE)
    no_of_tickets_sold = models.IntegerField()
    income = models.IntegerField()






