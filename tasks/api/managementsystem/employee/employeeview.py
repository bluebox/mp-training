
from django.http import JsonResponse

from .models import Employee, Complaint




def getlogin(emp_id,password):
    employee = Employee.objects.get(emp_id=emp_id,password=password)
    print (employee)
    if employee is not None:

        response={
        }
        print(response)
        return JsonResponse(response)
        # serializerdata = EmployeeSerializer(employee)
    return JsonResponse({"msg":"no such user exists"})




def post_complaint(data):
    serializerdata = PostComplaintSerializer(data=data)
    if serializerdata.is_valid():
        serializerdata.save()
        return JsonResponse(serializerdata.data)
def get_complaint(emp_id):
    complaint = Complaint.objects.filter(emp_id=emp_id)
    serializerdata=ComplaintSerializer(complaint,many=True)
    return JsonResponse(serializerdata.data,safe=False)\


def get_tasks(emp_id):
    employee = Employee.objects.get(emp_id=emp_id)
    facility_id = employee.facility_id;
    complaints = Complaint.objects.filter(facility_id=facility_id)
    serailizeddata = ComplaintSerializer(data=complaints, many=True)
    return JsonResponse(serailizeddata.data, safe=False)


