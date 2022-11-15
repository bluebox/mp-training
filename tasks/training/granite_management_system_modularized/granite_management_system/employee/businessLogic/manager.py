from rest_framework.response import Response

from employee.models import Employee, Role
from employee.serializers import employeeSerializer


def getEmployeeDetails(request,pk):
    if pk is not None:
        emp = Employee.objects.get(employee_id=pk)
        serialize = employeeSerializer(emp)
        print(emp)
        return Response(serialize.data)

    emp = Employee.objects.select_related()
    serialize = employeeSerializer(emp, many=True)
    return serialize.data

def addEmployee(request):
    serializer = employeeSerializer(data=request.data)
    print(serializer.is_valid())
    print(serializer.errors)
    employee_id = request.data['employee_id']
    employee_name = request.data['employee_name']
    doj = request.data['doj']
    role = request.data['role']
    salary = request.data['salary']
    phone = request.data['phone']
    email = request.data['email']
    address = request.data['address']
    print(role)
    emp = Employee(employee_id=employee_id, employee_name=employee_name, doj=doj,
                   role_id=Role.objects.get(role_name=role), salary=salary, phone=phone, email=email, address=address)
    emp.save()
    serialize = employeeSerializer(emp)
    return serialize.data

def updateEmployee(request, pk):
    serializer = employeeSerializer(data=request.data)
    print(serializer.is_valid())
    print(serializer.errors)
    employee_name = request.data['employee_name']
    doj = request.data['doj']
    role = request.data['role']
    salary = request.data['salary']
    phone = request.data['phone']
    email = request.data['email']
    address = request.data['address']
    emp = Employee(employee_id=pk, employee_name=employee_name, doj=doj,
                   role_id=Role.objects.get(role_name=role), salary=salary, phone=phone, email=email,
                   address=address)
    emp.save()
    serialize = employeeSerializer(emp)
    return serialize.data
