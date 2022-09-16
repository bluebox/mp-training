from django.shortcuts import render
from django.http import HttpResponse
from Pharmacy.models import *
# Create your views here.


def add_customer_details(request):
    if request.method == "POST":
        cust_id = request.POST['Customer Id']
        fname = request.POST['fname']
        lname = request.POST['lname']
        contact = request.POST['contact']
        email = request.POST['email']
        address = request.POST["address"]
        cust_details = Customer(cust_id=cust_id, first_name=fname, last_name=lname, contact=contact, email=email, address=address)
        cust_details.save()
    return render(request, 'customer_details.html')


def display_customer(request):
    det = Customer.objects.all()
    return render(request, 'display_customer.html', {'det': det})


def delete_customer(request, id):
    deta = Customer.objects.all()
    try:
        cust = Customer.objects.get(cust_id=id)
        cust.delete()
    except:
        return render(request, 'display_customer.html', {'det': deta, 'i': 'No data with id is found'})
    det = Customer.objects.all()
    return render(request, 'display_customer.html', {'det': det})


def add_employee_details(request):
    if request.method == "POST":
        emp_id = request.POST['Employee Id']
        fname = request.POST['fname']
        lname = request.POST['lname']
        doj = request.POST['doj']
        designation = request.POST['designation']
        job_type = request.POST['job_type']
        salary = request.POST["salary"]
        contact = request.POST['contact']
        email = request.POST['email']
        address = request.POST["address"]
        try:
            emp_details = Employee.objects.create(emp_id=emp_id, first_name=fname, last_name=lname, doj=doj, designation=designation, job_type=job_type, salary=salary, contact=contact, email=email, address=address)
        except:
            return render(request, 'employee_details.html', {'a': "Duplicate Value"})
    return render(request, 'employee_details.html')


def display_employee(request):
    det = Employee.objects.all()
    return render(request, 'display_employee.html', {'det': det})
