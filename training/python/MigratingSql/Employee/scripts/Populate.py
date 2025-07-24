from Employee.models import *
from django.db import connection
def run():
    # Departments.objects.bulk_create([Departments(dept_name=Departments.DepartmentChoices.RD),
    #                                  Departments(dept_name=Departments.DepartmentChoices.FSD),
    #                                  Departments(dept_name=Departments.DepartmentChoices.BD),
    #                                  Departments(dept_name=Departments.DepartmentChoices.PD),
    #                                  Departments(dept_name=Departments.DepartmentChoices.TS),
    #                                  Departments(dept_name=Departments.DepartmentChoices.ND)
    #                                  ])
    #
    # Designations.objects.bulk_create([Designations(designation=Designations.DesignationChoices.SSE),
    #                                   Designations(designation=Designations.DesignationChoices.NOD),
    #                                   Designations(designation=Designations.DesignationChoices.JSE),
    #                                   Designations(designation=Designations.DesignationChoices.ASE)
    #                                   ])
    #
    # ase=Designations.objects.get(designation=Designations.DesignationChoices.ASE)
    # jse = Designations.objects.get(designation=Designations.DesignationChoices.JSE)
    # sse = Designations.objects.get(designation=Designations.DesignationChoices.SSE)
    # nod = Designations.objects.get(designation=Designations.DesignationChoices.NOD)
    #
    #
    # rd=Departments.objects.get(dept_name=Departments.DepartmentChoices.RD)
    # fsd=Departments.objects.get(dept_name=Departments.DepartmentChoices.FSD)
    # bd=Departments.objects.get(dept_name=Departments.DepartmentChoices.BD)
    # pd=Departments.objects.get(dept_name=Departments.DepartmentChoices.PD)
    # ts=Departments.objects.get(dept_name=Departments.DepartmentChoices.TS)
    # nd=Departments.objects.get(dept_name=Departments.DepartmentChoices.ND)
    #
    #
    # Employees.objects.bulk_create([Employees(emp_id=1234,emp_name="Kanishka",dob='2020-04-03',designation=ase,date_joined='2020-06-15',dept=pd),
    #                                Employees(emp_id=1434,emp_name="Uday",dob='2019-04-03',designation=sse,date_joined='2021-06-15',dept=rd),
    #                                Employees(emp_id=1534,emp_name="Aanand",dob='2018-04-03',designation=jse,date_joined='2022-06-15',dept=bd),
    #                                Employees(emp_id=1274,emp_name="manu",dob='2020-04-03',designation=ase,date_joined='2023-06-15',dept=fsd),
    #                                Employees(emp_id=1294,emp_name="Madhu",dob='2023-04-03',designation=sse,date_joined='2024-06-15',dept=ts),
    #                                Employees(emp_id=1204,emp_name="Harshith",dob='2024-04-03',designation=nod,date_joined='2025-06-15',dept=nd)])
    #
    # DepartmentHeads.objects.bulk_create([DepartmentHeads(emp=Employees.objects.get(emp_id=1234),dept_head_since='2020-04-03'),
    #                                      DepartmentHeads(emp=Employees.objects.get(emp_id=1294),dept_head_since='2021-04-03'),
    #                                      DepartmentHeads(emp=Employees.objects.get(emp_id=1204),dept_head_since='2022-04-03')])
    #
    # PayScale.objects.bulk_create([PayScale(emp=Employees.objects.get(emp_id=1234),salary=77833),PayScale(emp=Employees.objects.get(emp_id=1434),salary=77833),PayScale(emp=Employees.objects.get(emp_id=1534),salary=77833),
    #                               PayScale(emp=Employees.objects.get(emp_id=1274),salary=77833),PayScale(emp=Employees.objects.get(emp_id=1294),salary=77833),PayScale(emp=Employees.objects.get(emp_id=1204),salary=77833)])
    #
    #
    #
    for emp in  Employees.objects.select_related("designation").all():
        print(emp.designation.designation)


    print(connection.queries)

    for obj_rel in Employees.objects.all():
        print(obj_rel.designation.designation)

    print(connection.queries)




