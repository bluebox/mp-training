
from django.shortcuts import render
from rest_framework.parsers import JSONParser
from django.http import HttpResponse
from .models import *
from .serializers import *
from django.http.response import JsonResponse

from django.core.files.storage import default_storage
# Create your views here.

##################CUSTOMER#####################
def customerApi(request, id=0):
    if request.method=='GET':
        customers = Customer.objects.all()
        customers_serializer = CustomerSerializer(customers, many=True)
        return JsonResponse(customers_serializer.data, safe=False)
    elif request.method=='POST':
        customer_data = JSONParser().parse(request)
        customers_serializer = CustomerSerializer(data = customer_data)
        if customers_serializer.is_valid():
            customers_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add.", safe=False)
    elif request.method=='PUT':
        customer_data = JSONParser().parse(request)
        customer = Customer.objects.get(cust_id = customer_data['cust_id'] )
        customers_serializer = CustomerSerializer(customer, data = customer_data)
        if customers_serializer.is_valid():
            customers_serializer.save()
            return JsonResponse("updated successfully", safe=False)
        return JsonResponse("Failed to update", safe=False)
    
    elif request.method == 'DELETE':
        customer = Customer.objects.get(cust_id=id)
        customer.delete()
        return JsonResponse("Deleted successfully", safe=False)

 


def SaveFile(request):
    file=request.FILES['uploadFile']
    file_name = default_storage.save(file.name,file)
    return JsonResponse(file_name, safe=False)

##############################Employee#############################



def employeeApi(request, id=0):
    if request.method=='GET':
        employees = Employee.objects.all()
        employees_serializer = EmployeeSerializer(employees, many=True)
        return JsonResponse(employees_serializer.data, safe=False)
    elif request.method=='POST':
        employee_data = JSONParser().parse(request)
        employees_serializer = EmployeeSerializer(data = employee_data)
        if employees_serializer.is_valid():
            employees_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add.", safe=False)
    elif request.method=='PUT':
        employee_data = JSONParser().parse(request)
        employee = Employee.objects.get(emp_id = employee_data['emp_id'] )
        employees_serializer = EmployeeSerializer(employee, data = employee_data)
        if employees_serializer.is_valid():
            employees_serializer.save()
            return JsonResponse("updated successfully", safe=False)
        return JsonResponse("Failed to update", safe=False)
    
    elif request.method == 'DELETE':
        employee = Employee.objects.get(emp_id=id)
        employee.delete()
        return JsonResponse("Deleted successfully", safe=False)

 


############################Distributor##########################


def distributorApi(request, id=0):
    if request.method=='GET':
        distributors = Distributor.objects.all()
        distributors_serializer = DistributorSerializer(distributors, many=True)
        return JsonResponse(distributors_serializer.data, safe=False)
    elif request.method=='POST':
        distributor_data = JSONParser().parse(request)
        distributors_serializer = DistributorSerializer(data = distributor_data)
        if distributors_serializer.is_valid():
            distributors_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add.", safe=False)
    elif request.method=='PUT':
        distributor_data = JSONParser().parse(request)
        distributor = Distributor.objects.get(dist_id = distributor_data['dist_id'] )
        distributors_serializer = DistributorSerializer(distributor, data = distributor_data)
        if distributors_serializer.is_valid():
            distributors_serializer.save()
            return JsonResponse("updated successfully", safe=False)
        return JsonResponse("Failed to update", safe=False)
    
    elif request.method == 'DELETE':
        distributor = Distributor.objects.get(dist_id=id)
        distributor.delete()
        return JsonResponse("Deleted successfully", safe=False)





#################################DOCTOR################################

def doctorApi(request, id=0):
    if request.method=='GET':
        doctors = Doctor.objects.all()
        doctors_serializer = DoctorSerializer(doctors, many=True)
        return JsonResponse(doctors_serializer.data, safe=False)
    elif request.method=='POST':
        doctor_data = JSONParser().parse(request)
        doctors_serializer = DoctorSerializer(data = doctor_data)
        if doctors_serializer.is_valid():
            doctors_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add.", safe=False)
    elif request.method=='PUT':
        doctor_data = JSONParser().parse(request)
        doctor = Doctor.objects.get(doc_id = doctor_data['doc_id'] )
        doctors_serializer = DoctorSerializer(doctor, data = doctor_data)
        if doctors_serializer.is_valid():
            doctors_serializer.save()
            return JsonResponse("updated successfully", safe=False)
        return JsonResponse("Failed to update", safe=False)
    
    elif request.method == 'DELETE':
        distributor = Doctor.objects.get(doc_id=id)
        distributor.delete()
        return JsonResponse("Deleted successfully", safe=False)






##############################Manufacturer##############################

def manufacturerApi(request, id=0):
    if request.method=='GET':
        manufacturers = Manufacturer.objects.all()
        manufacturers_serializer = ManufacturerSerializer(manufacturers, many=True)
        return JsonResponse(manufacturers_serializer.data, safe=False)
    elif request.method=='POST':
        manufacturer_data = JSONParser().parse(request)
        manufacturers_serializer = ManufacturerSerializer(data = manufacturer_data)
        if manufacturers_serializer.is_valid():
            manufacturers_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add.", safe=False)
    elif request.method=='PUT':
        manufacturer_data = JSONParser().parse(request)
        manufacturer = Manufacturer.objects.get(company_id = manufacturer_data['company_id'] )
        manufacturers_serializer = ManufacturerSerializer(manufacturer, data = manufacturer_data)
        if manufacturers_serializer.is_valid():
            manufacturers_serializer.save()
            return JsonResponse("updated successfully", safe=False)
        return JsonResponse("Failed to update", safe=False)
    
    elif request.method == 'DELETE':
        distributor = Manufacturer.objects.get(company_id=id)
        distributor.delete()
        return JsonResponse("Deleted successfully", safe=False)





























































'''

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


def delete_employee(request):
    if request.method == "POST":
        emp1 = request.POST["Id"]
    deta = Employee.objects.all()
    try:
        emp = Employee.objects.get(emp_id=emp1)
        emp.delete()
    except:
        return render(request, 'delete_employee.html', {'det': deta, 'i': 'No data with id is found'})
    det = Employee.objects.all()
    return render(request, 'display_employee.html', {'det': det})

'''