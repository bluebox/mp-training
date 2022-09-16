from django.shortcuts import render
from .models import *
# Create your views here.
def loginpage(request):
    return render(request, 'login.html')

def login(request):
    if request.method == 'GET':
        emp_id = request.GET.get('emp_id')
        password = request.GET.get('password')
        if Employee.objects.filter(emp_id=emp_id).exists() and Employee.objects.get(emp_id=emp_id).emp_password == password :
            data = {
                'name': Employee.objects.get(emp_id=emp_id).emp_name,
                'role': Employee.objects.get(emp_id=emp_id).emp_role,
                "image": Employee.objects.get(emp_id=emp_id).emp_pic,

            }
            return render(request, 'index.html', data)

#
#
#
# def login(request):
#     pass
