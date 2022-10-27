from django.db import models

# Create your models here.

# 1 User Table


class User(models.Model):
    GENDER_CHOICES = (
        ('M', 'Male'),
        ('F', 'Female'),
    )

    user_id = models.CharField(max_length=50, primary_key=True)
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    adhar_no = models.CharField(max_length=50, default="")
    gender = models.CharField(max_length=1, choices=GENDER_CHOICES)
    age = models.IntegerField(default=0)
    mobile_no = models.CharField(max_length=13)
    email = models.EmailField(max_length=50)
    city = models.CharField(max_length=50)
    state = models.CharField(max_length=50)
    pincode = models.CharField(max_length=12)
    password = models.CharField(max_length=50)
    security_ques = models.CharField(max_length=50, default="")
    security_ans = models.CharField(max_length=50, default="")

    def __str__(self):
        return self.first_name

# 2 Train Table


class Train(models.Model):
    train_no = models.CharField(max_length=50, primary_key= True)
    train_name = models.CharField(max_length=50)
    arrival_time = models.TimeField(auto_now=False, auto_now_add=False)
    departure_time = models.TimeField(auto_now=False, auto_now_add=False)
    availability_of_seats = models.IntegerField()
    train_date = models.DateField()

    def __str__(self):
        return self.train_no

# 3 Ticket Table


class Ticket(models.Model):
    STATUS_CHOICES = (
        ('WL', 'Waiting list'),
        ('CNF', 'Confirm'),
        ('RAC', 'RAC'),
    )
    id = models.AutoField(primary_key=True)
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)
    status = models.CharField(max_length=3, choices=STATUS_CHOICES)
    no_of_passengers = models.IntegerField()
    train_no = models.ForeignKey(Train, on_delete=models.CASCADE)

    def __str__(self):
        return self.id

# 4 Station Table


class Station(models.Model):
    station_no = models.CharField(max_length=20, primary_key=True)
    station_name = models.CharField(max_length=50)
    hault = models.IntegerField()
    arrival_time = models.TimeField(auto_now=False, auto_now_add=False)
    train_no = models.ForeignKey(Train, on_delete=models.CASCADE)

    def __str__(self):
        return self.station_no

# 5 TrainStatus Table


class TrainStatus(models.Model):
    train_no = models.ForeignKey(Train, on_delete=models.CASCADE,primary_key=True)
    b_seat1 = models.IntegerField()
    b_seat2 = models.IntegerField()
    w_seat1 = models.IntegerField()
    w_seat2 = models.IntegerField()
    a_seat1 = models.IntegerField()
    a_seat2 = models.IntegerField()
    fair2 = models.IntegerField()
    fair2 = models.IntegerField()

    def __str__(self):
        return self.a_seat1

# 6 Passenger Table


class Passenger(models.Model):
    GENDER_CHOICES = (
        ('M', 'Male'),
        ('F', 'Female'),
    )

    STATUS_CHOICES = (
        ('WL', 'Waiting list'),
        ('CNF', 'Confirm'),
        ('RAC', 'RAC'),
    )

    passenger_id = models.IntegerField(primary_key=True)
    pnr_no = models.IntegerField()
    age = models.IntegerField()
    gender = models.CharField(max_length=1, choices=GENDER_CHOICES)
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)
    reservation_status = models.CharField(max_length=3, choices=STATUS_CHOICES)
    seat_no = models.CharField(max_length=10)
    name = models.CharField(max_length=50)
    ticket_id = models.ForeignKey(Ticket, on_delete=models.CASCADE)

    def __str__(self):
        return self.passenger_id

# 7 Started Table


class Started(models.Model):
    train_no = models.ForeignKey(Train, on_delete=models.CASCADE, primary_key=True)
    station_no = models.ForeignKey(Station, on_delete=models.CASCADE)

    def __str__(self):
        return self.train_no

# 8 Stopped Table


class Stopped(models.Model):
    train_no = models.ForeignKey(Train, on_delete=models.CASCADE, primary_key=True)
    station_no = models.ForeignKey(Station, on_delete=models.CASCADE)

    def __str__(self):
        return self.train_no

# 9 Reaches Table


class Reach(models.Model):
    train_no = models.ForeignKey(Train, on_delete=models.CASCADE, primary_key=True)
    station_no = models.ForeignKey(Station, on_delete=models.CASCADE)
    timing = models.TimeField(auto_now=False, auto_now_add=False)

    def __str__(self):
        return self.train_no


# 10 Books Table


class Book(models.Model):
    id = models.IntegerField(primary_key=True)
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)

    def __str__(self):
        return self.id


# 11 Cancel Table


class Cancel(models.Model):
    id = models.IntegerField(primary_key=True)
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)
    passenger_id = models.ForeignKey(Passenger, on_delete=models.CASCADE)

    def __str__(self):
        return self.id
