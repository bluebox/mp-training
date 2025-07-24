from Employee.models import *
def run():
    PayScale.objects.all().delete()
    DepartmentHeads.objects.all().delete()
    Employees.objects.all().delete()
    Designations.objects.all().delete()
    Departments.objects.all().delete()

