from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from django.views import View
from django.views.decorators.csrf import csrf_exempt

from .serializers import EmployeeSerializer,FacilitySerializer
from rest_framework.parsers import JSONParser
from .forms import Employeeform,Complaintform,IssuedtoForm

 # Create your views here.
from .models import *

global i
i=0
# #
# def home(request):
#     return render(request, 'login.html')
#
# def login(request):
#     if request.method == 'GET':
#         emp_id = request.GET.get('emp_id')
#         password = request.GET.get('password')
#         loginas = request.GET.get('loginas')
#         employee=Employee.objects.get(emp_id=emp_id)
#         # devices=list(Employee_Devices.objects.get(emp_id=employee))
#         devices=Employee_Devices.objects.filter(emp_id=employee)
#         # devices=Employee_Devices.objects.prefetch_related(employee)
#         complaintform=Complaintform()
#         usercomplaints=Complaint.objects.filter(emp_id=employee)
#         yourwork=Issued_to.objects.filter(emp_id=employee)
#         if Employee.objects.filter(emp_id=emp_id).exists() and employee.emp_password == password and loginas== 'Employee':
#
#             data = {
#                 'name': Employee.objects.get(emp_id=emp_id).emp_name,
#                 'role': Employee.objects.get(emp_id=emp_id).emp_role,
#                 "image": Employee.objects.get(emp_id=emp_id).emp_pic,
#                 'devices' : devices,
#                 'complaintform': complaintform,
#                 'usercomplaints': usercomplaints,
#                 'yourwork':yourwork,
#
#             }
#             return render(request, 'index.html', data)
#         elif Employee.objects.filter(emp_id=emp_id).exists() and employee.emp_password== password and loginas=='Manager' and employee.emp_role=='manager':
#             form=Employeeform()
#             facility_id=Employee.objects.get(emp_id=emp_id).facility_id
#             print(facility_id)
#             complaints = Complaint.objects.filter(facility_id=facility_id , is_assigned=False)
#             issuedtoform=IssuedtoForm()
#             data = {
#                 'name': Employee.objects.get(emp_id=emp_id).emp_name,
#                 'role': Employee.objects.get(emp_id=emp_id).emp_role,
#                 "image": Employee.objects.get(emp_id=emp_id).emp_pic,
#                 'form': form,
#                 'complaints': complaints,
#                 'issuedtoform': issuedtoform,
#             }
#             return render(request, 'manager.html',data)
#         elif loginas == 'Manager':
#             return HttpResponse("your not a manager")
#
#
# def create(request):
#     if request.method == 'GET':
#         id=request.GET.get('emp_id')
#         name=request.GET.get('emp_name')
#         mobile=request.GET.get('emp_mobile')
#         email=request.GET.get('emp_email')
#         role=request.GET.get('emp_role')
#         pic=request.GET.get('emp_pic')
#         password=request.GET.get('emp_password')
#         facility_id=request.GET.get('facility_id')
#         facility=Facility.objects.get(facility_id=facility_id)
#         employee = Employee(emp_id=id, emp_name=name, emp_mobile=mobile, emp_email=email,emp_role=role, emp_pic=pic, emp_password=password, facility_id=facility)
#         employee.full_clean()
#         employee.save()
#         return HttpResponse("employee"+employee.emp_name+" created")
#         # return HttpResponse(facility_id)
#
#
# def complaint(request):
#     global i
#     if request.method =='GET':
#         emp_id= request.GET.get('emp_id')
#         item=request.GET.get('device_id')
#         descp=request.GET.get('comp_desc')
#         facility_id=request.GET.get('facility_id')
#         employee=Employee.objects.get(emp_id=emp_id)
#         device=Device.objects.get(device_id=item)
#         facility=Facility.objects.get(facility_id=facility_id)
#         complaint=Complaint(facility_id=facility, comp_id=i,emp_id=employee,device_id=device,comp_desc=descp)
#         complaint.save()
#         i += 1
#         return HttpResponse(item)
#
#
#
# def issuedto(request):
#     if request.method == 'GET':
#         complaint1=request.GET.get('comp_id')
#         complaint=Complaint.objects.get(comp_id=complaint1)
#         emp_id=request.GET.get('emp_id')
#         employee=Employee.objects.get(emp_id=emp_id)
#         status=request.GET.get('comp_status')
#         issuedto1=Issued_to(comp_id=complaint,emp_id=employee,comp_status=status)
#         complaint.is_assigned=True
#         complaint.save()
#         issuedto1.save()
#         return HttpResponse(issuedto1)
#
#
#
# def yourcomplaints(request):
#     emp_id=request.GET.get('name')
#     return HttpResponse(emp_id)
#

class EmployeeView(View):
    def get(self, request):
        name=request.GET.get('name')
        employee=Employee.objects.get(emp_name=name)
        serializerdata=EmployeeSerializer(employee)
        return JsonResponse(serializerdata.data)
    def post(self,request):
        # id=request.POST.get('id')
        # name=request.POST.get('name')
        # mobile=request.POST.get('mobile')
        # email=request.POST.get('email')
        # role=request.POST.get('role')
        # password=request.POST.get('password')
        # facility=request.POST.get('facility')
        employeedata=JSONParser().parse(request)
        serailizeddata=EmployeeSerializer(employeedata)
        if serailizeddata.is_valid():
            serailizeddata.save()
        return HttpResponse('serailizeddata')
        # department=Facility.objects.get(facility_id=facility)

class FacilityView(View):
    def post(self, request):

        id=request.POST.get('emp_id')
        device_id=request.POST.get('device_id')
        descp=request.POST.get('descp')
        device=Device.objects.get(device_id=device_id)
        employee=Employee.objects.get(emp_id=id)
        complaint=Complaint(emp_id=employee,device_id=device,comp_desc=descp)
        complaint.save()
        print(employee)
        return JsonResponse()

@csrf_exempt
def facility1(request):
    if request.method == 'POST':
        facility = JSONParser().parse(request)
        serailizeddata = FacilitySerializer(facility)
        if serailizeddata.is_valid():
            serailizeddata.save()
            return JsonResponse(serailizeddata)
        return JsonResponse(serailizeddata.errors, status=400)