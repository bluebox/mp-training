from django.db.models import Q
from rest_framework.parsers import JSONParser
import json

from .jwtauthentication import create_access_token
from .models import Employee, Complaint, Issued_to, Facility, Device
from .serializers import ComplaintSerializer, Issued_toSerializer, EmployeeSerializer, FacilitySerializer, \
    DeviceSerializer, PostComplaintSerializer


def post_complaint(request):
    facility_data = JSONParser().parse(request)
    serializerdata = PostComplaintSerializer(data=facility_data)
    if serializerdata.is_valid():
        serializerdata.save()
        return serializerdata.data

from django.core.paginator import Paginator

def page_with_3(request, object_list):
    paginator = Paginator(object_list, 3)
    try:
        page_number = int(request.GET.get('page'))
    except:
        page_number=1
    if not page_number:
        page_number = 1
    if page_number > paginator.num_pages:
        page_number = paginator.num_pages
    elif page_number < 1:
        page_number = 1
    page_obj = paginator.get_page(page_number)
    return page_obj, paginator.num_pages, page_number


def listing(request, object_list):
    paginator = Paginator(object_list, 10)
    try:
        page_number = int(request.GET.get('page'))
    except:
        page_number=1
    if not page_number:
        page_number = 1
    if page_number > paginator.num_pages:
        page_number = paginator.num_pages
    elif page_number < 1:
        page_number = 1
    page_obj = paginator.get_page(page_number)
    return page_obj, paginator.num_pages, page_number


def get_tasks(request):
    id = request.GET.get('id')
    employee = Employee.objects.get(emp_id=id)
    text = request.GET.get('text')
    assigned_to = request.GET.get('assignedto')
    is_assigned = request.GET.get('isassigned')
    query = (Q(facility_id=employee.facility_id) &
        Q(device_id__device_name__icontains=text)
        | Q(emp_id__emp_name__icontains=text)
        | Q(comp_desc__icontains=text))

    if assigned_to == 'all':
        if is_assigned == 'all':
            query = query
        else:
            if is_assigned == 'asigned':
                is_assigned = True
            if is_assigned == 'yettobeassigned':
                is_assigned = False
            query=query & Q(is_assigned=is_assigned)
        complaints = Complaint.objects.filter(query)
        complaints_list, total_pages, current_page = listing(request, complaints)
        serialized_data = ComplaintSerializer(complaints_list, many=True)
        return serialized_data.data, total_pages, current_page

    else:
        if is_assigned == 'all':
            query=query & Q(assigned_to__icontains=assigned_to)
        else:
            if is_assigned == 'assigned':
                is_assigned = True
            if is_assigned == 'yettobeassigned':
                is_assigned = False
            query = query & (Q(is_assigned=is_assigned) & Q(assigned_to__icontains=assigned_to))
        complaints = Complaint.objects.filter(query)
        complaints_list, total_pages, current_page = listing(request, complaints)
        serialized_data = ComplaintSerializer(complaints_list, many=True)
        return serialized_data.data, total_pages, current_page



def update_status_fun(request):
    message = request.GET.get('message')
    if message == 'completed':
        complaint = Complaint.objects.get(comp_id=request.GET.get('comp_id'))
        complaint.is_completed = True
        complaint.save()
    issued_complaint = Issued_to.objects.get(comp_id=request.GET.get('comp_id'))
    issued_complaint.comp_status = message
    issued_complaint.save()
    serialized_data = Issued_toSerializer(issued_complaint)
    return serialized_data.data
def complaint_search(request):
    text = request.GET.get('text')
    id= request.GET.get('id')
    is_completed = request.GET.get('iscompleted')
    query=(Q(emp_id=id)&
            Q(comp_id__icontains=text)
            | Q(comp_desc__icontains=text)
            | Q(device_id__device_name__icontains=text))
    if is_completed=='all' or not is_completed:
        query=query
    else:
        if is_completed == 'completed':
            is_completed = True
        else:
            is_completed = False
        query = query & Q(is_completed=is_completed)
    complaints = Complaint.objects.filter(query)
    complaints_list, total_pages, current_page = listing(request, complaints)
    serialized_data = ComplaintSerializer(complaints_list, many=True)
    return serialized_data.data, total_pages, current_page



def edit_profile(request):

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

    return serialized_data.data

def assign_task(request):
    emp_id = int(request.GET.get('emp_id'))
    comp_id = request.GET.get('comp_id')
    comp_status = 'assigned'
    employee = Employee.objects.get(emp_id=emp_id)
    complaint = Complaint.objects.get(comp_id=comp_id)
    complaint.is_assigned = True
    complaint.assigned_to = employee.emp_name
    complaint.save()
    issued_to = Issued_to(emp_id=employee, comp_id=complaint, comp_status=comp_status)
    issued_to.save()
    serialized_data = Issued_toSerializer(issued_to)
    return serialized_data.data



def search_employees(request):
    name = request.GET.get('name')
    employee = Employee.objects.filter(emp_name__icontains=name)
    serialized_data = EmployeeSerializer(employee, many=True)
    return serialized_data.data
def get_facilities(request):
    facilities = Facility.objects.all()
    serialized_data = FacilitySerializer(facilities, many=True)
    return serialized_data.data


def login(request):
    id = request.GET.get('id')
    employee = Employee.objects.get(emp_id=id)
    serialized_data = EmployeeSerializer(employee)
    access_token = create_access_token(employee.emp_id)
    return access_token,serialized_data.data



def post_employee(request):
    employee_data = JSONParser().parse(request)
    serialized_data = EmployeeSerializer(data=employee_data)
    if serialized_data.is_valid():
        serialized_data.save()
    return serialized_data.data

def get_employee_byname(request):
    name = request.GET.get('name')
    employee = Employee.objects.get(emp_name=name)
    serialized_data = EmployeeSerializer(employee)
    return serialized_data.data

def get_employee_list(request):
    id = request.GET.get('id')
    employee = Employee.objects.get(emp_id=id)
    employee_list = Employee.objects.filter(facility_id=employee.facility_id)
    serialized_data = EmployeeSerializer(employee_list, many=True)
    return serialized_data.data

def post_facility(request):
    facility_data = JSONParser().parse(request)
    serialized_data = FacilitySerializer(data=facility_data)
    if serialized_data.is_valid():
        serialized_data.save()
        return serialized_data.data
    else:
        return ("error")


def get_devices(request):
    id = request.GET.get('id')
    device = Device.objects.filter(facility_id=int(id))
    device_list, total_pages, current_page = page_with_3(request, device)
    serialized_data = DeviceSerializer(device_list, many=True)
    return serialized_data.data,total_pages,current_page


def add_device(request):
    data = JSONParser().parse(request)
    serialized_data = DeviceSerializer(data=data)
    if serialized_data.is_valid():
        serialized_data.save()
        return serialized_data.data

def delete_device(request):
    id = request.GET.get('id')
    device = Device.objects.get(device_id=int(id))
    device.delete()


def create_employee(request):
    employee_data = JSONParser().parse(request)
    serialized_data = EmployeeSerializer(data=employee_data)
    if serialized_data.is_valid():
        serialized_data.save()
    return serialized_data.data
def delete_employee(request):
    emp_id= request.GET.get('emp_id')
    employee= Employee.objects.get(emp_id=emp_id)
    employee.delete()



def get_assigned_tasks(request):
    emp_id = request.GET.get('id')
    complaint=Issued_to.objects.filter(emp_id=emp_id)
    serialized_data= Issued_toSerializer(complaint, many=True)
    return serialized_data.data

def get_complaint_search(request):
    is_completed = request.GET.get('iscompleted')
    text = request.GET.get('text')
    emp_id = request.GET.get('id')
    query = (Q(emp_id=emp_id),
             Q(comp_id__comp_desc__icontains=text)
             | Q(comp_id__emp_id__emp_name__icontains=text)
             | Q(comp_id__device_id__device_name__icontains=text))

    if is_completed != 'all':
        if is_completed == 'completed':
            is_completed = 'completed'
        else:
            is_completed = ''
        query = Q(comp_status=is_completed) & query
    tasks = Issued_to.objects.filter(query)
    serialized_data = Issued_toSerializer(tasks, many=True)
    return serialized_data.data



def get_profile(request):
    emp_id = request.GET.get('emp_id')
    total_complaints=len(Complaint.objects.filter(emp_id=emp_id))
    problems_solved=len(Issued_to.objects.filter(emp_id=emp_id))
    return total_complaints,problems_solved


#
#

# def post_complaint(request):
#     data = JSONParser().parse(request)
#     serialized_data = PostComplaintSerializer(data=data)
#     if serialized_data.is_valid():
#         serialized_data.save()
#         return serialized_data.data


# def get_task_with_all(request):
#     id = request.GET.get('id')
#     employee = Employee.objects.get(emp_id=id)
#     text = request.GET.get('text')
#     complaints = Complaint.objects.filter(
#         Q(facility_id=employee.facility_id),
#         Q(device_id__device_name__icontains=text)
#         |Q(emp_id__emp_name__icontains=text)
#         |Q(comp_desc__icontains=text)
#
#     )
#     complaints_list, total_pages, current_page = listing(request, complaints)
#     serialized_data = ComplaintSerializer(complaints_list, many=True)
#
#     return Response({
#         'pageItems': serialized_data.data,
#         'totalPages': total_pages,
#         'page': current_page,
#     })

# def get_task_all_assigned(request):
#     id = request.GET.get('id')
#     employee = Employee.objects.get(emp_id=id)
#     text = request.GET.get('text')
#     is_assigned= request.GET.get('isassigned')
#     if is_assigned=='assigned':
#         is_assigned = True
#     else :
#         is_assigned=False
#
#     complaints = Complaint.objects.filter(
#         Q(facility_id=employee.facility_id),
#         Q(is_assigned=is_assigned),
#         Q(device_id__device_name__icontains=text)
#         | Q(emp_id__emp_name__icontains=text)
#         | Q(comp_desc__icontains=text)
#
#     )
#     complaints_list, total_pages, current_page = listing(request, complaints)
#     serialized_data = ComplaintSerializer(complaints_list, many=True)
#
#     return Response({
#         'pageItems': serialized_data.data,
#         'totalPages': total_pages,
#         'page': current_page,
#
#     })
# def get_task_is_assigned_all(request):
#     id = request.GET.get('id')
#     employee = Employee.objects.get(emp_id=id)
#     text = request.GET.get('text')
#     assigned_to= request.GET.get('assignedto')
#     complaints = Complaint.objects.filter(
#         Q(facility_id=employee.facility_id),
#         Q(assigned_to__icontains=assigned_to),
#         Q(device_id__device_name__icontains=text)
#         | Q(emp_id__emp_name__icontains=text)
#         | Q(comp_desc__icontains=text)
#     )
#     complaints_list, total_pages, current_page = listing(request, complaints)
#     serialized_data = ComplaintSerializer(complaints_list, many=True)
#     return Response({
#         'pageItems': serialized_data.data,
#         'totalPages': total_pages,
#         'page': current_page,
#
#     })
# def get_complaint(emp_id):
#     complaint = Complaint.objects.filter(emp_id=emp_id)
#     serializerdata=ComplaintSerializer(complaint,many=True)
#     return JsonResponse(serializerdata.data,safe=False)


# def get_tasks(emp_id):
#     employee = Employee.objects.get(emp_id=emp_id)
#     facility_id = employee.facility_id;
#     complaints = Complaint.objects.filter(facility_id=facility_id)
#     serailizeddata = ComplaintSerializer(data=complaints, many=True)
#     return JsonResponse(serailizeddata.data, safe=False)
