from itertools import count

from django.shortcuts import render, redirect
from .models import *
from django.http import HttpResponse
from django.contrib.auth import authenticate
from django.contrib import messages

# # Create your views here.
# from ..templates import *

def login(request):
    return render(request, 'login.html')


def index(request):
    return render(request, 'signup.html')


def login1(request):
    if request.method == "POST":
        x = request.POST['name']
        y = request.POST['password']
        if Customer.objects.filter(f_name=x).exists():
            if Customer.objects.get(f_name=x).password == y:
                return render(request, 'home.html')
            else:
                return HttpResponse("no output")
        else:
            return HttpResponse("Username doesn't match")
    else:
        return render(request, 'login.html')

# def login(request):
#     if request.method=='POST':
#         obj=Customer()
# def xyz(request, exception):
#     return render(request, 'login.html', status=404)


def signup(request):
    if request.method == 'POST':
        uname = request.POST['uname']
        f_name = request.POST['first_name']
        l_name = request.POST['last_name']
        mail = request.POST['email']
        password = request.POST['password']
        conf_pass = request.POST['confirm_password']
        a = request.POST['person']
        img = request.POST['img']
        if password == conf_pass:
            if a == "buyer":
                if Customer.objects.filter(uname=uname).exists():
                    if Customer.objects.filter(mail=mail).exists():
                        messages.info(request,'username or mail already exists')
                        return redirect('signup')
                else:
                    if Customer.objects.filter(mail=mail).exists():
                        messages.info(request,'username or mail already exists')
                        return redirect('signup')
                    else:
                        x = Customer.objects.create(uname=uname, f_name=f_name, l_name=l_name, mail =mail, password=password)
                        x.save()
                        return render(request,'home.html')
            if a == "seller":
                return HttpResponse("Welcome  "+uname)
        else:
            messages.info(request, 'Password doesnot matches')
            return redirect('signup')
    else:
        return render(request, 'signup.html')



