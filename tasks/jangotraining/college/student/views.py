from django.shortcuts import render
from django.http import HttpResponse
from .models import *
# Create your views here.
def index(request):
    return render(request, "index.html")


def studentlogin(request):
    return render(request, "studentlogin.html")


def stafflogin(request):
    return render(request, "stafflogin.html")


def register(request):
    return render(request, "register.html")

def login(request):
    if request.method=='GET':
        user_id=request.GET.get('user_id')
        password=request.GET.get('password')
        # Student.objects.get(student_id=user_id).student_password == password
        if request.GET.get('users')=='Student':
            if Student.objects.filter( student_id=user_id).exists() and Student.objects.get(student_id=user_id).student_password == password:
                return render(request,'home.html',{'name':Student.objects.get(student_id=user_id).student_name })
            else:
                return HttpResponse('your not in database')
        elif request.GET.get('users') =='Professor':
            if Professor.objects.get(professor_id=user_id).professor_password== password :
                return render(request,'home.html',{'name':Professor.objects.get(professor_id=user_id).professor_name})


        elif request.GET.get('users') =='Hod':
            pass


        return HttpResponse(request.GET.get('user_id'))



def student_details(request):
    if request.method=='GET':
        stu_id=request.GET.get('stu_id')
        # studentitem=Student.objects.get(student_)
        name=Student.objects.get(student_id=stu_id)
        attendance=Student_Attendance.objects.get(student_id=name).attendance
        return HttpResponse(str(stu_id)+name.student_name+"  "+str(attendance))