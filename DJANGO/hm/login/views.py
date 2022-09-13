from django.http import HttpResponse
from django.shortcuts import render , redirect
from django.contrib import messages
from .models import*


def index(request):
    return render(request, 'index.html')


def about(request):
    print("hiiiiiiiiiiii")
    return render(request, 'about.html')
def whatwedo(request):
    return render(request, 'whatwedo.html')

def career(request):
    return render(request, 'career.html')


def contactus(request):
    return render(request, 'contact.html')

def emplogin(request):
         if request.method == "POST":
              loginid =request.POST.get("username","")
              password =request.POST.get("password","")
              print(loginid,password)
              logindetail = login.objects.filter(loginid=loginid)
              if len(logindetail)==0:
                 messages.info(request,'User not registered')
                 return render(request, 'emplogin.html')

              if logindetail[0].password==password:

               return render(request, 'homehr.html')

              else:
               messages.info(request, 'Incorrect password')
               return render(request, 'emplogin.html')


         return render(request, 'emplogin.html')





def homehr(request):
     return render(request,'homehr .html')





def base(request):
     return render(request,'base.html')