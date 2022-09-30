import json
from django.http import HttpResponse, JsonResponse
from django.template.context_processors import request
from django.views import View
from rest_framework.decorators import api_view
from rest_framework.views import APIView

from .serializers import EmployeeSerializer, Issued_toSerializer, ComplaintSerializer, DeviceSerializer, \
    FacilitySerializer
from rest_framework.parsers import JSONParser

from .models import *

global i
i = 0

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
def devicename(request):
    device=Device.objects.get(device_id=request.GET.get('device_id'))
    serailizeddata=DeviceSerializer(device)
    return JsonResponse(serailizeddata.data)
def complaintItem(request):
    complaint=Complaint.objects.get(comp_id=request.GET.get('comp_id'))
    serailizeddata=ComplaintSerializer(complaint)
    return JsonResponse(serailizeddata.data)


def searchemployee( request):
    name=request.GET.get('name')
    employee=Employee.objects.filter(emp_name__contains=name)
    serailizeddata=EmployeeSerializer(employee,many=True)
    return JsonResponse(serailizeddata.data, safe=False)


def facilities(request):
    facilities=Facility.objects.all()
    serailizeddata=FacilitySerializer(facilities,many=True)
    return JsonResponse(serailizeddata.data, safe=False)

@api_view()
def updatestatus(request):
    issuedcomplaint=Issued_to.objects.get(comp_id=request.GET.get('comp_id'))
    issuedcomplaint.comp_status="complated"
    issuedcomplaint.save()
    serailizeddata=Issued_toSerializer(issuedcomplaint)
    return JsonResponse(serailizeddata.data)
# def employeedevices(request):
#     devices=
class DeviceView(View):
    def get(self, request):
        id=request.GET.get('id')
        print(id)
        device=Device.objects.get(device_id=id)

        serailizeddata=DeviceSerializer(device)
        return JsonResponse(serailizeddata.data,safe=False)


class ComplaintView(APIView):
    def get(self, request):
        id = request.GET.get('id')
        employee = Employee.objects.get(emp_id=id)
        complaint = Complaint.objects.filter(emp_id=employee)
        complaintdata = ComplaintSerializer(complaint, many=True)
        return JsonResponse(complaintdata.data, safe=False)

    def post(self, request):
        body = request.body.decode('utf-8')
        body = json.loads(body)
        facility_id = body['facility_id']
        emp_id = body['emp_id']
        device_id = body['device_id']
        descp = body['comp_desc']
        print(facility_id)
        print(emp_id)

        device1 = Device.objects.get(device_id=device_id)
        employee = Employee.objects.get(emp_id=emp_id)
        facility = Facility.objects.get(facility_id=facility_id)
        complaint = Complaint(emp_id=employee, device_id=device1, comp_desc=descp, facility_id=facility)
        complaint.save()
        serailizeddata = ComplaintSerializer(complaint)
        print("sertialxrttgvh:", serailizeddata.data)
        # if serailizeddata.is_valid():
        #     serailizeddata.save()
        print(employee)
        return JsonResponse(serailizeddata.data)


class LoginView(View):
    def get(self, request):
        id = request.GET.get('id')
        employee = Employee.objects.get(emp_id=id)
        serializerdata = EmployeeSerializer(employee)
        return JsonResponse(serializerdata.data)


class EmployeeView(APIView):
    def get(self, request):
        name = request.GET.get('name')
        employee = Employee.objects.get(emp_name=name)
        serializerdata = EmployeeSerializer(employee)
        return JsonResponse(serializerdata.data, safe=False)

    def post(self, request):
        employeedata = JSONParser().parse(request)
        serailizeddata = EmployeeSerializer(employeedata)
        if serailizeddata.is_valid():
            serailizeddata.save()
        return HttpResponse('serailizeddata')
        # department=Facility.objects.get(facility_id=facility)


class FacilityView(APIView):

    def get(self, request):
        id = request.GET.get('id')
        employee = Employee.objects.get(emp_id=id)
        facility_id=employee.facility_id
        employeelist=Employee.objects.filter    (facility_id=facility_id)
        serailizeddata=EmployeeSerializer(employeelist,many=True)
        return JsonResponse(serailizeddata.data, safe=False)


# class EmployeeView1(viewsets.ModelViewSet):
#     queryset = Complaint.objects.all()
#     serializer_class = ComplaintSerializer
class TasksView(APIView):
    def get(self, request):
        id= request.GET.get('id')
        employee = Employee.objects.get(emp_id=id)
        tasks = Issued_to.objects.filter(emp_id=employee)
        complaintdata = Issued_toSerializer(tasks, many=True)
        return JsonResponse(complaintdata.data, safe=False)


class ManagerView(APIView):
    def get(self, request):
        id= request.GET.get('id')
        employee = Employee.objects.get(emp_id=id)
        facility_id=employee.facility_id;
        complaints=Complaint.objects.filter(facility_id=facility_id)
        serailizeddata=ComplaintSerializer(complaints,many=True)
        return JsonResponse(serailizeddata.data,safe=False)

class AssignView(APIView):
    def get(self, request):
        emp_id= int(request.GET.get('emp_id'))
        comp_id= request.GET.get('comp_id')
        comp_status='assigned'
        employee= Employee.objects.get(emp_id=emp_id)
        complaint= Complaint.objects.get(comp_id=comp_id)
        complaint.is_assigned=True
        complaint.save()
        issuedto=Issued_to(emp_id=employee,comp_id=complaint,comp_status=comp_status)
        issuedto.save()
        serailizeddata=Issued_toSerializer(issuedto)
        return JsonResponse(serailizeddata.data, safe=False)


class EditView(APIView):
    def post(self, request):
        emp_id=request.GET.get('id')
        body = request.body.decode('utf-8')
        body = json.loads(body)
        employee= Employee.objects.get(emp_id=emp_id)
        employee.emp_name=body['emp_name']
        employee.emp_email  = body['emp_email']
        employee.emp_mobile=body['emp_mobile']
        facility= body['facility_id']
        facilityobj=Facility.objects.get(facility_id=facility)
        employee.facility_id=facilityobj
        employee.emp_role= body['emp_role']
        employee.save()
        serailizeddata=EmployeeSerializer(employee)

        return JsonResponse(serailizeddata.data)