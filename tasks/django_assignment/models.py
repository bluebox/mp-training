from django.db import models
from django.contrib.auth.models import User
from django.core.exceptions import ValidationError

def validateName(name):
    flag = 1
    for i in name:
        if i != " " and i.isalpha() == False:
            flag = 0
            break
    if not flag:
        raise ValidationError(
            "name should only contains alphabets and spaces",
            params={'value':name},
        )

def validateDept(dept):
    if len(dept) < 3:
        raise ValidationError(
            "department name must be atleast 3 characters",
            params={'value':dept}
        )

# --- validators --- 

""" 
validators are to to validate on fields on django models 
functions should be defined and passed to validators argument"""

# Example of validators

"""
class Employee(models.Model):
    name = models.CharField(max_length=30, validators=[validateName])
    department = models.CharField(max_length=30, validators=[validateDept])
"""

# managed option in Meta class

""" managed is one of the options in Meta class 
if managed is set to True -> 
1. changes made in models will be affected in the db and this will be taken care by django

2. django will take care of primary key creation even 
if you dont create one 

3. if your model has many-to-many 
relationship, django creates default junction model to 
handle many to many relationship between your source and target models 

if manged is set to False -> 
- any creation, updation, deletion in the models will not be affected in the db

- You as a user has to handle primary key creation and has to 
create your own junction Model(Table) for source and target models 

- Creation of junction model can be achieved by using .through fields
"""

# example of managed = False
# This wont work as we set managed to False, we have to create our own
# primary key

"""
class Employee(models.Model):
    user_id = models.OneToOneField(User, on_delete=models.CASCADE)
    name = models.CharField(max_length=30, validators=[validateName])
    department = models.CharField(max_length=30, validators=[validateDept])

    class Meta:
        managed = False
"""

# Example 1 - managed is True in both models(tables)
# when any changes are made to the model, after running migrations, a new migrations file will be created
# in which all the changes are affected in the database

"""
class Emp(models.Model):
    emp_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=30)
    department = models.CharField(max_length=30)

class Lead(models.Model):
    teamLead_id = models.ManyToManyField(Emp)
    lead_name = models.CharField(max_length=30)"""

# Example 2 - managed is False in one Model(Group) and managed is True in one Model(Student)
# when any changes are made to the Group model, after running migrations, migration file will not be created
# and hence when you visit admin page you will get an error

"""
class Student(models.Model):
    emp_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=30)
    department = models.CharField(max_length=30)

class Group(models.Model):
    teamLead_id = models.ManyToManyField(Student)
    lead_name = models.CharField(max_length=30)

    class Meta:
        managed = False"""

# Example 2 - managed is False in both models
# when any changes are made to the any model, after running migrations, migration file will not be created
# and hence when you visit admin page you will get an error

"""
class Teacher(models.Model):
    emp_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=30)
    department = models.CharField(max_length=30)

    class Meta:
        managed = False

class Subject(models.Model):
    teamLead_id = models.ManyToManyField(Teacher)
    lead_name = models.CharField(max_length=30)
    role = models.CharField(max_length=30)

    class Meta:
        managed = False"""