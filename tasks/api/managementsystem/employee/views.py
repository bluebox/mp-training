import json
from django.http import JsonResponse
from rest_framework.decorators import api_view
from rest_framework.views import APIView
from .jwtauthentication import create_access_token
from .serializers import EmployeeSerializer, Issued_toSerializer, ComplaintSerializer, DeviceSerializer, \
    FacilitySerializer, EmployeeDeviceSerializer, PostComplaintSerializer, \
    ComplaintWithDeviceSerializer
from rest_framework.parsers import JSONParser
from .models import *

global i
i = 0


def hello(request):
    return JsonResponse('hello', safe=False)

#
# def device_name(request):
#     try:
#         device = Device.objects.get(device_id=request.GET.get('device_id'))
#         serialized_data = DeviceSerializer(device)
#         return JsonResponse(serialized_data.data)
#     except Exception as e:
#         return str(e)


def getstatus(request):
    try:
        comp_id = request.GET.get('id')
        complaint = Issued_to.objects.get(comp_id=comp_id)
        serialized_data = Issued_toSerializer(complaint)
        return JsonResponse(serialized_data.data)
    except:
        response = {
            "msg": "Complaint does not exist"
        }
        return JsonResponse(response)


def search_employee(request):
    try:
        name = request.GET.get('name')
        employee = Employee.objects.filter(emp_name__icontains=name)
        serialized_data = EmployeeSerializer(employee, many=True)
        return JsonResponse(serialized_data.data, safe=False)
    except Exception:
        print("Employee does not exist")
        return JsonResponse("employee does not exists", safe=False)


def facilities(request):
    facilities = Facility.objects.all()
    serialized_data = FacilitySerializer(facilities, many=True)
    return JsonResponse(serialized_data.data, safe=False)


@api_view()
def update_status(request):
    message = request.GET.get('message')
    if message == 'completed':
        complaint= Complaint.objects.get(comp_id=request.GET.get('comp_id'))
        complaint.is_completed = True
        complaint.save()
    issued_complaint = Issued_to.objects.get(comp_id=request.GET.get('comp_id'))
    issued_complaint.comp_status = message
    issued_complaint.save()
    serialized_data = Issued_toSerializer(issued_complaint)
    return JsonResponse(serialized_data.data)


def devices_of_employee(request):
    emp_id = request.GET.get('emp_id')
    devices = Employee_Devices.objects.filter(emp_id=emp_id)
    serialized_data = EmployeeDeviceSerializer(devices, many=True)
    return JsonResponse(serialized_data.data, safe=False)


class DeviceView(APIView):
    def get(self, request):
        device = Device.objects.all()
        serialized_data = DeviceSerializer(device,many=True)
        return JsonResponse(serialized_data.data, safe=False)

    def post(self, request):
        data=JSONParser().parse(request)
        serialized_data = DeviceSerializer(data=data)
        if serialized_data.is_valid():
            serialized_data.save()
            return JsonResponse(serialized_data.data, safe=False)




class ComplaintView(APIView):
    def get(self, request):
        emp_id = request.GET.get('id')
        complaint = Complaint.objects.filter(emp_id=emp_id)
        serialized_data = ComplaintWithDeviceSerializer(complaint,many=True)
        return JsonResponse(serialized_data.data ,safe=False)
    def post(self, request):
        data = JSONParser().parse(request)
        serialized_data=PostComplaintSerializer(data=data)
        print (11)
        # serialized_data.save()
        if serialized_data.is_valid():
            print(22)
            serialized_data.save()
            return JsonResponse(serialized_data.data, safe=False)

    def delete(self, request):
        comp_id = request.GET.get('comp_id')
        complaint = Complaint.objects.get(comp_id=comp_id)
        complaint.delete()
        response = {
            "msg": "complaint is deleted successfully"
        }
        return JsonResponse(response)


class LoginView(APIView):
    def get(self, request):
        try:
            id = request.GET.get('id')
            employee = Employee.objects.get(emp_id=id)
            serialized_data = EmployeeSerializer(employee)
            access_token = create_access_token(employee.emp_id)
            # refresh_token = create_refresh_token(Employee.emp_id)

            # UserToken.objects.create(
            #     user_id=Employee.emp_id,
            #     token=refresh_token,
            #     expired_at=datetime.datetime.utcnow() + datetime.timedelta(days=5)
            # )
            response = {
                "userdetails": serialized_data.data,
                'token':access_token,
            }
            return JsonResponse(response)

        except Exception as e:
            response = {
                "msg": str(e.message)
            }
            return JsonResponse(response)


class EmployeeView(APIView):
    def get(self, request):
        try:
            name = request.GET.get('name')
            employee = Employee.objects.get(emp_name=name)
            serialized_data = EmployeeSerializer(employee)
            return JsonResponse(serialized_data.data, safe=False)
        except Exception as e:
            response = {
                "msg": str(e)
            }
            return JsonResponse(response)

    def post(self, request):
        employee_data = JSONParser().parse(request)
        serialized_data = EmployeeSerializer(data=employee_data)

        if serialized_data.is_valid():
            serialized_data.save()
        return JsonResponse(serialized_data.data)


class FacilityView(APIView):

    def get(self, request):
        id = request.GET.get('id')
        employee = Employee.objects.get(emp_id=id)
        facility_id = employee.facility_id
        employee_list = Employee.objects.filter(facility_id=facility_id)
        serialized_data = EmployeeSerializer(employee_list, many=True)
        return JsonResponse(serialized_data.data, safe=False)

    def post(self, request):
        facility_data= JSONParser().parse(request)
        serialized_data = FacilitySerializer(data=facility_data)
        if serialized_data.is_valid():
            serialized_data.save()
            return JsonResponse(serialized_data.data, safe=False)




class TasksView(APIView):
    def get(self, request):
        id = request.GET.get('id')
        employee = Employee.objects.get(emp_id=id)
        tasks = Issued_to.objects.filter(emp_id=employee)
        complaint_data = Issued_toSerializer(tasks, many=True)
        return JsonResponse(complaint_data.data, safe=False)


class ManagerView(APIView):
    def get(self, request):
        id = request.GET.get('id')
        employee = Employee.objects.get(emp_id=id)
        facility_id = employee.facility_id;
        complaints = Complaint.objects.filter(facility_id=facility_id,is_assigned=False)
        serialized_data = ComplaintSerializer(complaints, many=True)
        return JsonResponse(serialized_data.data, safe=False)


class AssignView(APIView):
    def get(self, request):
        emp_id = int(request.GET.get('emp_id'))
        comp_id = request.GET.get('comp_id')
        comp_status = 'assigned'
        employee = Employee.objects.get(emp_id=emp_id)
        complaint = Complaint.objects.get(comp_id=comp_id)
        complaint.is_assigned = True
        complaint.save()
        issued_to = Issued_to(emp_id=employee, comp_id=complaint, comp_status=comp_status)
        issued_to.save()
        serialized_data = Issued_toSerializer(issued_to)
        return JsonResponse(serialized_data.data, safe=False)


class EditView(APIView):
    def post(self, request):
        emp_id = request.GET.get('id')
        body = request.body.decode('utf-8')
        body = json.loads(body)
        employee = Employee.objects.get(emp_id=emp_id)
        employee.emp_name = body['emp_name']
        employee.emp_email = body['emp_email']
        employee.emp_mobile = body['emp_mobile']
        facility = body['facility_id']
        employee.emp_password = body['emp_password']

        employee.facility_id = Facility.objects.get(facility_id=facility)
        employee.emp_role = body['emp_role']
        employee.save()
        serialized_data = EmployeeSerializer(employee)

        return JsonResponse(serialized_data.data)


class CreateEmployee(APIView):
    def post(self, request):
        employee_data = JSONParser().parse(request)
        serialized_data = EmployeeSerializer(data=employee_data)

        if serialized_data.is_valid():
            serialized_data.save()
        return JsonResponse(serialized_data.data)
    def delete(self, request):
        emp_id= request.GET.get('emp_id')
        employee= Employee.objects.get(emp_id=emp_id)
        employee.delete()
        response = {
            "msg": "complaint is deleted successfully"
        }
        return JsonResponse(response)

def getassignedto(request):
    try:
        assignedto = Issued_to.objects.get(comp_id=request.GET.get('comp_id'))
        complaint = Complaint.objects.get(comp_id=request.GET.get('comp_id'))
        emp_id = assignedto.emp_id
        response = {'emp_name': emp_id.emp_name,
                    'status': complaint.is_assigned}
        return JsonResponse(response)
    except:
        complaint = Complaint.objects.get(comp_id=request.GET.get('comp_id'))
        response = {'comp_status': complaint.is_assigned}
        return JsonResponse(response)
