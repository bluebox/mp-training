from django.shortcuts import render


# Create your views here.
from django.http import HttpResponse

def home(request):
    return render(request,'HomePage.html', {'title':'Welcome'})

def homepage(request, username):
    dict = {'title':'Welcome Back Buddy, I\'am Home Page ', 'username':username}
    return render(request,'HomePage.html', {'title':dict["title"],'username':dict["username"]})

def profilepage(request):
    return render(request,'HomePage.html', {'title':'Welcome to your profile page'})

def loginpage(request):
    return render(request,'loginpage.html')

def evenvalues(request):
    n=range(1,30+1)
    print(n)
    return render(request,'DjangoTag.html',{'N' : n})
