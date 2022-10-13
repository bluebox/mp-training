"""Views"""
from rest_framework.parsers import JSONParser
from django.http.response import JsonResponse
from django.core.files.storage import default_storage
from .models import Customer, Employee, Doctor, Distributor, Drug, Manufacturer
from .serializers import CustomerSerializer, EmployeeSerializer, DoctorSerializer
from .serializers import DrugSerializer, ManufacturerSerializer, DistributorSerializer

# Create your views here.

##################CUSTOMER#####################
def customerApi(request, id=0):
    """API"""
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
        manufacturer = Manufacturer.objects.get(company_id=id)
        manufacturer.delete()
        return JsonResponse("Deleted successfully", safe=False)





#######################################DRUG##################################

def drugApi(request, id=0):
    if request.method=='GET':
        drugs = Drug.objects.all()
        drugs_serializer = DrugSerializer(drugs, many=True)
        return JsonResponse(drugs_serializer.data, safe=False)
    elif request.method=='POST':
        drug_data = JSONParser().parse(request)
        drugs_serializer = DrugSerializer(data = drug_data)
        if drugs_serializer.is_valid():
            drugs_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add.", safe=False)
    elif request.method=='PUT':
        drug_data = JSONParser().parse(request)
        drug = Drug.objects.get(drug_id = drug_data['drug_id'] )
        drugs_serializer = DrugSerializer(drug, data = drug_data)
        if drugs_serializer.is_valid():
            drugs_serializer.save()
            return JsonResponse("updated successfully", safe=False)
        return JsonResponse("Failed to update", safe=False)
    
    elif request.method == 'DELETE':
        drug = Drug.objects.get(drug_id=id)
        drug.delete()
        return JsonResponse("Deleted successfully", safe=False)





















































