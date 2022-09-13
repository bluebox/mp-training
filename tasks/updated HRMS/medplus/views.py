from django.shortcuts import render
from django.http import  HttpResponse
from .models import log,personal_details,department,insurance,leave
from django.contrib.auth.models import User
from django.contrib.auth import authenticate,login,logout
from django.contrib.auth.decorators import login_required
# Create your views here.
def index(request):
    return render(request,'medplus/index.html')

def signin(request):
    username = request.POST["username"]
    # email = request.POST["email"]
    password = request.POST["password"]

    b = User(username = username,password = password)
    b.save()
    return render(request,'medplus/start.html')


def start(request):
    user_name = request.POST["UN"]
    password = request.POST["PW"]
    obj = authenticate(username=user_name, password=password)
    print(type(obj))
    print(obj.username)
    if obj is None:
        return HttpResponse("login failed")
    elif obj.username == "hari":
        a = leave.objects.all()
        return render(request,'medplus/requests.html',{'data': a})
    else:
        login(request,obj)
        return render(request,'medplus/createprofile.html')
@login_required
def features(request):
    username = request.POST["username"]
    fullmname = request.POST["fullname"]
    dob = request.POST["dob"]
    contact = request.POST["contact"]
    b = personal_details(full_name=fullmname,dob = dob,contact_no = contact)
    b.login_id = log.objects.get(user_name = username)
    b.dept_id = department.objects.get(id = 1)
    b.insurance = insurance.objects.get(id = 1)
    b.save()

    return render(request,'medplus/sucess.html')
@login_required
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
def leaves(request):
    return HttpResponse("SUCCESSFULLY APPILIED")
def signup(request):
    return render(request,'medplus/SIGNUP.html')
def acs(request):
    return HttpResponse("account created")
def so(request):
    return HttpResponse("loggedout suvccessfully")