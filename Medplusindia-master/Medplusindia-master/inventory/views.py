from django.shortcuts import render
from inventory.models import User

# Create your views here.

def index(request):
    return render(request,'index.html')

def wish(request):
    if request.method=='POST':
        uname=request.POST['username']
        email=request.POST['email']
        passwd=request.POST['passwd']
        phone=request.POST['phone']
        gender=request.POST['gender']

        saving=User(username=uname,phone=phone,gender=gender,emailid=email,password=passwd)
        saving.save()
    return render(request,'wish.html',{'uname':uname,'mail':email,'passwd':passwd,'phone':phone,'gender':gender})