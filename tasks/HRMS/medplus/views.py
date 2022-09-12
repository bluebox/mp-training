from django.shortcuts import render
from django.http import  HttpResponse
from .models import login,personal_details,department,insurance
# Create your views here.
def index(request):
    return render(request,'medplus/index.html')

def signin(request):
    username = request.POST["username"]
    email = request.POST["email"]
    password = request.POST["password"]

    a = login(user_name = username,email = email , password = password)
    a.save()

    return render(request,'medplus/createprofile.html',{'user' : username})

def features(request):
    username = request.POST["username"]
    fullmname = request.POST["fullname"]
    dob = request.POST["dob"]
    contact = request.POST["contact"]
    b = personal_details(full_name=fullmname,dob = dob,contact_no = contact)
    b.login_id = login.objects.get(user_name = username)
    b.dept_id = department.objects.get(id = 1)
    b.insurance = insurance.objects.get(id = 1)
    b.save()

    return render(request,'medplus/sucess.html')
def TASKS(request):
    return render(request,'medplus/tasks.html')
def atts(request):
    return HttpResponse("attendence")
def ins(request):
    return HttpResponse("insurance")
def ta(request):
    return HttpResponse("will review it and share the response!!!")
def lv(request):
    return render(request,'medplus/leaves.html')
def leave(request):
    return HttpResponse("SUCCESSFULLY APPILIED")