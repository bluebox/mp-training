from django.shortcuts import render
from Employee.models import Departments,Employees,Designations
from django.http import JsonResponse

# Create your views here.
def Populate(request):

    # if request.method=='GET':
    #     Departments.objects.create(1,"R&D")
    #     Departments.object.create(2,"full stack")
    #     Departments.object.create(3,"business")
    #     Departments.object.create(4,"QA")
    #     Departments.object.create(5,"testing")
    #     Departments.object.create(6,"back_end")


    #     Employees.objects.bulk_create([Employees(2998,"Uday",'2000-06-04','2022-06-22',1),
    #                                    Employees(2598,"kanis",'2000-06-04','2023-06-22',2),
    #                                    Employees(2978,"Ram",'2000-06-04','2021-06-22',3),
    #                                    Employees(2498,"ravi",'2000-06-04','2021-06-22',3),
    #                                    Employees(2558,"Arun",'2000-06-04','2019-06-22',5)])
    depts=list(Departments.objects.values())
    return JsonResponse(depts,safe=False)

def ExecuteRead(request):
    pass

def UpdateData(request):
    pass

def DeleteData(request):
    pass
