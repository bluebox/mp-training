from django.db import models
import uuid


# Create your models here.

class Department(models.Model):
    dept_id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    dept_name = models.CharField(null=False, blank=False, max_length=30)
    dept_description = models.CharField(null=False, blank=False, max_length=300)

    def __str__(self):
        return f"{self.dept_name} {self.dept_description}"


class User(models.Model):
    user_id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    user_name = models.CharField(null=False, blank=False, max_length=30)
    user_email  = models.CharField(null=False, blank=False, max_length=50)
    user_phone_no = models.IntegerField(null=False, blank=False)
    user_dept_id = models.ForeignKey('Department', on_delete=models.CASCADE)
    user_address = models.CharField(null=False, blank=False, max_length=70)
    user_password = models.CharField(null=False, blank=False, max_length=30)


class Issue(models.Model):
    issue_id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    issue_creator_id = models.ForeignKey('User', on_delete=models.CASCADE)
    issue_name = models.CharField(null=False, blank=False, max_length=50)
    issue_description = models.CharField(null=False, blank=False, max_length=300)
    issue_dept_id = models.ForeignKey('Department', on_delete=models.CASCADE)
    issue_created_date = models.DateField(null=False, blank=False)
    issue_status = models.CharField(null=False, blank=False, default="open", max_length=30)


class Developers(models.Model):
    developer_id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    developer_name = models.CharField(null=False, blank=False, max_length=30)
    developer_mobile_no = models.IntegerField(null=False, blank=False)
    developer_email = models.CharField(null=False, blank=False, max_length=50)
    developer_password = models.CharField(null=False, blank=False, max_length=30)
    developer_username = models.CharField(null=False, blank=False, max_length=30)
    developer_address = models.CharField(null=False, blank=False, max_length=30)


class Tracking(models.Model):
    tracking_id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    issue_id = models.ForeignKey('Issue', on_delete=models.CASCADE)
    issue_solver_id = models.ForeignKey('Developers', on_delete=models.CASCADE)
    issue_status = models.CharField(null=False, blank=False, default='open', max_length=30)
    issue_status_description = models.CharField(null=False, blank=False, max_length=300)
    issue_deadline = models.DateField(null=False, blank=False)


class Product(models.Model):
    product_id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    product_description = models.CharField(null=False, blank=False, max_length=300)
    product_name = models.CharField(null=False, blank=False, max_length=30)
    product_dept_id = models.ForeignKey('Department', on_delete=models.CASCADE)
    product_developer_id = models.ForeignKey('Developers', on_delete=models.CASCADE)


class Bug(models.Model):
    bug_id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    bug_creator_id = models.ForeignKey('Developers', on_delete=models.CASCADE)
    bug_name = models.CharField(null=False, blank=False, max_length=30)
    bug_dept_id = models.ForeignKey('Department', on_delete=models.CASCADE)
    bug_description = models.CharField(null=False, blank=False, max_length=300)


class Comments(models.Model):
    comment_id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    user_id = models.ForeignKey('User', on_delete=models.CASCADE)
    issue_id = models.ForeignKey('Issue', on_delete=models.CASCADE)
    dept_id = models.ForeignKey('Department', on_delete=models.CASCADE)
    comment_date = models.DateField(null=False, blank=False)
    comment_description = models.CharField(null=False, blank=False, max_length=300)
