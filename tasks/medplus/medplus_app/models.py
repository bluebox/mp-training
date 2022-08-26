from django.db import models
from django.db import models

# Create your models here.
class GeeksModel(models.Model):
	title = models.CharField(max_length = 200)
	description = models.TextField()
	def __str__(self):
		return self.title




class Geeks(models.Model):
	title = models.CharField(max_length = 200)
	description = models.TextField()
	def __str__(self):
		return self.title




class employees1(models.Model):
	name = models.CharField(max_length=30)
	phone_number = models.IntegerField()
	email_id = models.EmailField(max_length=50)
	role = models.CharField(max_length=100)
	salary = models.IntegerField()

	def __str__(self):
		return "{} {} {} {}".format(self.name,self.phone_number,self.email_id,self.salary,self.role)

