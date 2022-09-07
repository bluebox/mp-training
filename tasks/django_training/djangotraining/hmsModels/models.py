from django.db import models

class Customer(models.Model):
    customerId = models.IntegerField(primary_key=True)
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    address = models.CharField(max_length=50)
    status = models.CharField(max_length=50)
    contactNumber = models.PositiveBigIntegerField()



class Employee(models.Model):
    employeeId = models.IntegerField(primary_key=True)
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    department = models.CharField(max_length=30)
    address = models.CharField(max_length=50)
    contactNumber = models.PositiveBigIntegerField()
    username = models.CharField(max_length=50)
    password = models.CharField(max_length=50)

class RoomClass(models.Model):
    classId = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=50)
    price = models.IntegerField()

class Payment(models.Model):
    paymentId = models.IntegerField(primary_key=True)
    customer = models.ForeignKey(Customer,on_delete=models.CASCADE)
    type = models.CharField(max_length=50)
    paymentDate = models.DateField()

class Room(models.Model):
    roomId = models.IntegerField(primary_key=True)
    classRoom = models.ForeignKey(RoomClass,on_delete=models.CASCADE)
    price = models.IntegerField()
    description = models.TextField()


class Prices(models.Model):
    daysRange = models.IntegerField(primary_key=True)
    classRoom=models.ForeignKey(RoomClass,on_delete=models.CASCADE)
    price = models.IntegerField()

class Reservation(models.Model):
    reservationId = models.IntegerField(primary_key=True)
    customer = models.ForeignKey(Customer, on_delete=models.CASCADE)
    room=models.ForeignKey(Room,  on_delete=models.CASCADE)
    reservationDate= models.DateField()
    dateIn= models.DateField()
    dateOut= models.DateField()
    daysRangeStay = models.ForeignKey(Prices, on_delete=models.CASCADE)


class Transaction(models.Model):
    transactionId = models.IntegerField(primary_key=True)
    transactionName = models.CharField(max_length=50)
    customer=models.ForeignKey(Customer, on_delete=models.CASCADE)
    employee = models.ForeignKey(Employee, on_delete=models.CASCADE)
    reservation = models.ForeignKey(Reservation, on_delete=models.CASCADE)
    transactionDate = models.DateField()
    payment = models.ForeignKey(Payment,on_delete=models.CASCADE)




