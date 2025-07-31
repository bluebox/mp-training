
from django.conf import settings
from django.contrib.auth.models import AbstractUser
from django.db import models
from django.core.validators import RegexValidator
from django.db.models import Q, F
from datetime import datetime


class CustomUser(AbstractUser):
	ROLE_CHOICES = [
		('employee', 'Employee'),
		('hr', 'HR'),
		('manager', 'Manager'),
		('ceo', 'CEO'),
	]
	role = models.CharField(max_length=20, choices=ROLE_CHOICES, default='employee')

	def is_employee(self):
		return self.role == 'employee'

	def is_manager(self):
		return self.role == 'manager'

	def is_hr(self):
		return self.role == 'hr'

	def is_ceo(self):
		return self.role == 'ceo'


class Departments(models.Model):
	class DepartmentChoices(models.TextChoices):
		RD = "R&D", "Research and Development"
		FSD = "FSD", "Full Stack Developer"
		BD = "BD", "Backend Development"
		PD = "PD", "Production"
		TS = "TS", "Transactions"
		ND = "ND", "Not Determined"

	dept_name = models.CharField(max_length=3, blank=False, choices=DepartmentChoices.choices,
								 default=DepartmentChoices.ND, unique=True)



class Designations(models.Model):
	class DesignationChoices(models.TextChoices):
		ASE = "ASE", "Associate Software Engineer"
		JSE = "JSE", "Junior Software Engineer"
		SSE = "SSE", "Senior Software Engineer"
		NOD = "NOD", "Not Assigned"

	designation = models.CharField(max_length=3, unique=True, default=DesignationChoices.NOD,
								   choices=DesignationChoices.choices)



class Employees(models.Model):
	user = models.OneToOneField(settings.AUTH_USER_MODEL, on_delete=models.CASCADE, related_name='employee_profile')
	emp_id = models.CharField(
		max_length=7,
		primary_key=True,
		validators=[RegexValidator(regex=r'^OTG\d{4}$', message="Employee ID must be in format OTGXXXX")],
	)
	emp_name = models.CharField(max_length=20, blank=False)
	dob = models.DateField(blank=False, null=True)
	date_joined = models.DateField(blank=False, default=datetime.today)
	dept = models.ForeignKey(Departments, on_delete=models.CASCADE, related_name='employees')
	is_active = models.BooleanField(default=False)

	class EmployeeManager(models.Manager):
		def get_queryset(self):
			return super().get_queryset()

		def get_active_only(self):
			return self.get_queryset().filter(is_active=True)

		def get_inactive_only(self):
			return self.get_queryset().filter(is_active=False)

	objects = EmployeeManager()




class EmployeeJobDetails(models.Model):
	emp = models.ForeignKey(Employees, on_delete=models.CASCADE, related_name='job_details')
	designation = models.ForeignKey(Designations, on_delete=models.CASCADE, related_name='designated_employees')
	effective_from = models.DateField(blank=False, null=True)
	effective_till = models.DateField(blank=False, null=True)
	is_active = models.BooleanField(default=False)
	team_id = models.PositiveIntegerField(blank=False, null=True)

	class Meta:
		constraints = [
			models.UniqueConstraint(
				fields=['emp'],
				condition=Q(is_active=True),
				name='unique_job_at_a_time'
			),
			models.CheckConstraint(
				check=Q(effective_from__lt=F('effective_till')),
				name='check_valid_effective_dates'
			)
		]



class EmployeeOfficeAddressDetails(models.Model):
	emp = models.OneToOneField(Employees, on_delete=models.CASCADE, related_name='address_details')
	country = models.CharField(max_length=20, blank=False)
	city = models.CharField(max_length=20, blank=False)
	state = models.CharField(max_length=20, blank=False)



class ReportingManagers(models.Model):
	emp = models.ForeignKey(Employees, on_delete=models.CASCADE, blank=False, related_name='reporting_managers')
	manager_since = models.DateField(blank=False)


class PayScale(models.Model):
	emp = models.ForeignKey(Employees, on_delete=models.CASCADE, blank=False, related_name="pay_scales")
	salary = models.DecimalField(max_digits=10, decimal_places=2, default=0.00, blank=True)




