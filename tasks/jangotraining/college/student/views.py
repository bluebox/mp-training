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
                return render(request,'studentlogin.html',{'name':Student.objects.get(student_id=user_id).student_name })
            else:
                return HttpResponse('your not in database')
        elif request.GET.get('users') =='Professor':
            if Professor.objects.get(professor_id=user_id).professor_password== password :
                return render(request,'stafflogin.html',{'name':Professor.objects.get(professor_id=user_id).professor_name})


        elif request.GET.get('users') =='Hod':
            pass


        return HttpResponse(request.GET.get('user_id'))



def student_details(request):
    if request.method=='GET':
        stu_id=request.GET.get('stu_id')
        # studentitem=Student.objects.get(student_)
        name=Student.objects.get(student_id=stu_id)
        attendance=Student_Attendance.objects.get(student_id=name).attendance
        # return HttpResponse(str(stu_id)+name.student_name+"  "+str(attendance))
        data={
            'name':name.student_name,
            'id':stu_id,
            'attendance':attendance,
            'mobile':name.Student_mobile

        }
        return render(request,'staff.html',data)


def studentreg(request):
    if request.method=="GET":
        branch=Branch.objects.get(branch_id=request.GET.get('branch'))
        fee=Fee_report.objects.get(fee_id=5005)
        hall=Exam_branch.objects.get(hall_ticket_number=18857)
        student=Student(student_id=request.GET.get('stu_id'),student_name=request.GET.get('name'),
        Student_mobile=request.GET.get('number'),Student_email=request.GET.get('email'),
        branch_id=branch, fee_id=fee, hall_ticket_number=hall)
        student.save()
        return HttpResponse(student.student_name)

def edit(request):
    if request.method=='GET':
        student = Student.objects.get(student_id=request.GET.get('id'))
        name= student.student_name
        student.student_name=request.GET.get('name')
        print(name)
        student.save()
        return HttpResponse(name+' is changed to  '+student.student_name)
    return HttpResponse( ' is changed to  ' )