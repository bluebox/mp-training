from django.shortcuts import render
from django.http import HttpResponse
from .models import *
from django.contrib.auth.decorators import login_required
# Create your views here.
def index(request):
    
    return render(request,'index.html')



@login_required
def login(request):
    if request.method == "POST":
        
        user_type = request.POST.get('dropdown')
        login_id = request.POST.get('login_id')
        password1 = request.POST.get('password')
        if int(user_type) == 1:
            if students.objects.filter(student_id=login_id).exists():
                student= students.objects.get(student_id=login_id)
                pass1=student.password
                if password1==pass1:
                    attendance = students_attendence.objects.get(student_id = login_id)
                    fee_details  = student_fee_details.objects.get(student_id = login_id)
                    context={
                        "student":student,'attendance':attendance,'fee_details':fee_details
                    }
                    return render(request,'students_page.html',context)
                else:
                    return HttpResponse('incorrect password')
            else:
                return HttpResponse('invalid student user')

        elif int(user_type) == 2:
            if professor.objects.filter(id=login_id).exists():
                professor1= professor.objects.get(id=login_id)
                pass1 = professor1.password
                if password1==pass1:
                    return render(request,'employee_page.html')
                else:
                    return HttpResponse('incorrect password')
            else:
                return HttpResponse('invalid user')
        elif int(user_type) == 3:
            if admin_details.objects.filter(admin_id=login_id).exists():
                admin_id = admin_details.objects.get(admin_id=login_id)
                pass1 = admin_id.password
                if pass1==password1:
                    context ={
                        'admin':admin_id
                    }
                    return render(request,'admin_page.html',context)
                else:
                    return HttpResponse('incorrect password')
            else:
                return HttpResponse('invalid admin user')
    else:
        return HttpResponse("invalid page   ")




def student_attendance1(request,stud_id):
    if request.method =="POST":
        students.objects.get(stud_id)
