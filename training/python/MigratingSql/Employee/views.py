from django.shortcuts import render
from Employee.models import Departments,Employees,Designations
from django.http import JsonResponse

# Create your views here.
def Populate(request):

    if request.method=='GET':
        pass
        # Designations.objects.bulk_create([Designations(designation=Employees.DesignationChoices.ASE), Designations(designation=Employees.DesignationChoices.JSE), Designations(designation=Employees.DesignationChoices.SSE)])



        # Departments.objects.create(dept_id=1,dept_name="R&D")
        # Departments.objects.create(dept_id=2,dept_name="M&S")
        # Departments.objects.create(dept_id=3,dept_name="full stack")
        # Departments.objects.create(dept_id=4,dept_name="business")
        # Departments.objects.create(dept_id=5,dept_name="QA")
        # Departments.objects.create(dept_id=6,dept_name="testing")
        # Departments.objects.create(dept_id=7,dept_name="back_end")


        # Employees.objects.bulk_create([Employees(emp_id=2998,emp_name="Uday",dob='2000-06-04',designation=Designations.objects.get(designation="associate software engineer"),date_joined='2022-06-22',dept_id=Departments.objects.get(dept_id=2)),
        #                                Employees(emp_id=2598,emp_name="kanis",dob='2000-06-04',designation=Designations.objects.get(designation="junior software engineer"),date_joined='2023-06-22',dept_id=Departments.objects.get(dept_id=3)),
        #                                Employees(emp_id=2978,emp_name="Ram",dob='2000-06-04',designation=Designations.objects.get(designation="senior software engineer"),date_joined='2021-06-22',dept_id=Departments.objects.get(dept_id=4)),
        #                                Employees(emp_id=2498,emp_name="ravi",dob='2000-06-04',designation=Designations.objects.get(designation="associate software engineer"),date_joined='2021-06-22',dept_id=Departments.objects.get(dept_id=5)),
        #                                Employees(emp_id=2558,emp_name="Arun",dob='2000-06-04',designation=Designations.objects.get(designation="junior software engineer"),date_joined='2019-06-22',dept_id=Departments.objects.get(dept_id=6))])


        


def ExecuteRead(request):
    if request.method=='GET':

        depts=list(Departments.objects.values())
        return JsonResponse(depts,safe=False)

def UpdateData(request):
    pass

def DeleteData(request):
    pass
