
from django.shortcuts import render,redirect
from .models import employees1
from django.http import HttpResponse


def index(request):
  return  render(request,'index.html')

def view_all_employee(request):
    emps= employees1.objects.all()
    context = {
      'emps': emps
    }
    return render(request, 'view_all_employee.html', context)


def delete_an_employee(request,email_id):
  object1 = employees1.objects.get(email_id=email_id)
  object1.delete()
  return redirect('/delete_an_employee1')

def delete_an_employee1(request):
  emps = employees1.objects.all()
  context={
    'emps':emps
  }
  return render(request,'delete_an_employee.html',context)

def filter(request):
  if request.method=='POST':
    emp = employees1.objects.all()
    name1 = request.POST['name']
    email_id1 = request.POST['email_id']
    role1 = request.POST['role']
    if name1:
      emp = emp.filter(name__icontains=name1) 
    if email_id1:
      emp = emp.filter(email_id__icontains=email_id1)
    if role1:
      emp = emp.filter(role__icontains=role1)

    context = {
      'emps': emp
      }
    return  render(request,'view_all_employee.html',context)
  elif request.method=="GET":
    return render(request,'filter.html')
  else:
    return HttpResponse("error occured")


def add_an_employee(request):
  if request.method=='POST':
    name1 = request.POST['name']
    email_id1 = request.POST['email_id']
    phone_number_1 = int(request.POST['phone_number'])
    role1 = request.POST['role']
    salary1 = int(request.POST['salary'])
    obj = employees1(name=name1,phone_number=phone_number_1,email_id=email_id1,role=role1,salary=salary1)
    obj.save()
    return HttpResponse("details added successfully")
  elif request.method=="GET":
    return  render(request,'add_an_employee.html')
  else:
    return HttpResponse("error")