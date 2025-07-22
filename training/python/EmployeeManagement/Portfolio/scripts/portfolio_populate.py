from django.db import connection
from django.utils import timezone
from Portfolio.models import *
def run():
    # DepartmentModel.objects.create()
    # DepartmentModel.objects.create(dept_name="R&D")
    # DepartmentModel.objects.create(dept_name="Python")
    # DepartmentModel.objects.create(dept_name="React")
    # DepartmentModel.objects.create(dept_name="Django")
    # DepartmentModel.objects.create(dept_name="Logging")
    # DepartmentModel.objects.create(dept_name="Testing")
    #
    # EmployeeModel.objects.create(employee_name="Kanishka", employee_email="kanishka.medplus@gmail.com", birth_date='2003-12-04',
    #                              mobile_number=9126354633,date_joined='2025-07-22',)

    print(connection.queries)


