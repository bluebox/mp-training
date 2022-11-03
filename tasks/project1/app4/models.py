from django.db import models
from django.contrib import admin




class Airport(models.Model):
    airport_id = models.PositiveIntegerField(primary_key=True)
    airport_name = models.CharField(max_length=50, blank=True, null=True)
    airport_owner = models.CharField(max_length=50, blank=True, null=True)
    city = models.CharField(max_length=50, blank=True, null=True)


class Terminal(models.Model):
    terminal_id = models.PositiveIntegerField(primary_key=True)
    terminal_description = models.TextField(max_length=200, blank=True, null=True)
    airport_id = models.ForeignKey(Airport, on_delete=models.CASCADE)

def  upload_path(instance, filename):
    return '/'.join(['covers', str(instance.airlines_name), filename])


class Airlines(models.Model):
    airlines_name = models.CharField(max_length=50, primary_key=True)
    airlines_id = models.PositiveIntegerField(blank=True, null=True,default=None)
    origin_country = models.CharField(max_length=50, blank=True, null=True,default=None)
    airport_id = models.ForeignKey(Airport, on_delete=models.CASCADE,default=None)
    terminal_id = models.ForeignKey(Terminal, on_delete=models.CASCADE,default=None)
    airlines_img=models.ImageField(default=None, null=True, upload_to=upload_path)


class Flight(models.Model):
    flight_id = models.PositiveIntegerField(primary_key=True)
    flight_name = models.CharField(max_length=50, blank=True, null=True)
    destination = models.CharField(max_length=50, blank=True, null=True)
    model_number = models.CharField(max_length=50, blank=True, null=True)
    capacity = models.PositiveIntegerField(blank=True, null=True)
    weight = models.PositiveIntegerField(blank=True, null=True)
    airport_id = models.ForeignKey(Airport, on_delete=models.CASCADE)
    airlines_name = models.ForeignKey(Airlines, on_delete=models.CASCADE)


def upload_paths(instance, filename):
    return '/'.join(['profile', str(instance.passenger_name), filename])


class Passenger(models.Model):
    passenger_id = models.PositiveIntegerField(primary_key=True)
    passenger_name = models.CharField(max_length=50, blank=True, null=True)
    mobile_number = models.CharField(max_length=50, blank=True, null=True)
    email = models.EmailField(max_length=50,null=True)
    passenger_city = models.CharField(max_length=50, blank=True, null=True)
    password=models.CharField(max_length=50, blank=True, null=True)
    is_admin=models.CharField(max_length=50, default="false", null=True)
    passenger_image=models.ImageField(default=None, null=True, upload_to=upload_paths)


class Ticket(models.Model):
    ticket_id = models.PositiveIntegerField(primary_key=True,auto_created=True)
    booking_from = models.CharField(max_length=50, blank=True, null=True)
    booking_to = models.CharField(max_length=50, blank=True, null=True)
    travel_date = models.DateField()
    flight_id = models.ForeignKey(Flight, on_delete=models.CASCADE)
    passenger_id = models.ForeignKey(Passenger, on_delete=models.CASCADE)
    seat_no = models.CharField(max_length=50, blank=True, null=True)


class Schedule(models.Model):
    departure_time = models.DateField(primary_key=True)
    arrival_time = models.DateField()
    flight_id = models.OneToOneField(Flight, on_delete=models.CASCADE)
    airport_id = models.ForeignKey(Airport, on_delete=models.CASCADE)
    terminal_id = models.ForeignKey(Terminal, on_delete=models.CASCADE)


class Staff(models.Model):
    staff_id = models.PositiveIntegerField(primary_key=True)
    staff_designation = models.CharField(max_length=50, blank=True, null=True)
    staff_name = models.CharField(max_length=50, blank=True, null=True)
    staff_phone = models.PositiveIntegerField()
    airport_id = models.ForeignKey(Airport, on_delete=models.CASCADE)
    airlines_name = models.ForeignKey(Airlines, on_delete=models.CASCADE)


class StaffShifts(models.Model):
    shift_from = models.DateField('default=timezone.now')
    shift_to = models.DateField('default=timezone.now')
    staff_id = models.OneToOneField(Staff, on_delete=models.CASCADE)
    flight_id = models.ForeignKey(Flight, on_delete=models.CASCADE)


class Luggage(models.Model):
    luggage_type = models.CharField(max_length=50, blank=True, null=True)
    luggage_weight = models.PositiveIntegerField()
    ticket_id = models.OneToOneField(Ticket, on_delete=models.CASCADE)





# def nameFile(instance, filename):
#     return '/'.join(['images', str(instance.name), filename])
#
#
# class UploadImageTest(models.Model):
#     name = models.CharField(max_length=100)
#     image = models.ImageField(upload_to=nameFile, blank=True, null=True)


# def namefile(instance, filename):
#     return '/'.join(['', str(instance.name), filename])


# class UploadProfileTest(models.Model):
#     name = models.CharField(max_length=100)
#     image = models.ImageField(upload_to=namefile, blank=True, null=True)
# class Profile(models.Model):
#     user = models.OneToOneField(User, on_delete=models.CASCADE)
#     image = models.ImageField(default='default.png', upload_to = 'profile_pics')
#
#     def __str__(self):
#         return f'{self.user.username} Profile'




# class User(AbstractUser):
#     name = models.CharField(max_length=500)
#     email = models.CharField(max_length=500, unique=True)
#     password = models.CharField(max_length=255)
#     username= None
#     user_type_choice = (
#         ('passenger', 'passenger'),
#         ('staff', 'staff'),
#         ('admin','admin')
#     )
#     user_type = models.CharField(choices=user_type_choice, default='passenger', max_length=9)
#     USERNAME_FIELD = 'email'
#     REQUIRED_FIELDS = []