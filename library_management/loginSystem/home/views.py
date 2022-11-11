from datetime import datetime, timedelta
from urllib import request
from django.shortcuts import render, redirect
from django.contrib.auth.models import User
from django.contrib.auth import authenticate,logout,login
from django.contrib import messages
from home.models import book

# Create your views here.
def index(request):
    if request.user.is_anonymous:
        cont={'user':" Guest User"}
        return render(request,"index.html",cont)      
    else:
        cont={'user':request.user}
        return render(request,"index.html",cont)

def loginUser(request):
   
    if request.method =='POST':
        username=request.POST.get('username')
        password=request.POST.get('password')

        user = authenticate(username=username,password=password)
        if user is not None:
            login(request,user)
            messages.success(request, 'login sucessful')
            return render(request,"base.html")
        else:
            messages.error(request, 'Invalid Credentials')
            return render(request,"login.html")
    return render(request,"login.html")
    # No backend authenticated the credentials

    

def signup(request):
    # if request.user.is_anonymous:
    #     return redirect('/login')
    if request.method =='POST':
         username=request.POST.get('username')
         first_name=request.POST.get('first_name')
         last_name=request.POST.get('last_name')
         email=request.POST.get('email')
         password=request.POST.get('pass')
         user = User.objects.create_user(username,email,password)
         user.first_name=first_name
         user.last_name=last_name
         user.roll="admin"

         user.save()
         return redirect('/login')
    return render(request,"signup.html")

def logoutUser(request):
    logout(request)
    return redirect("/login")

def display(request):
    b=book.objects.all()
    context={'book':b}
    print(b)
    return render(request,"display.html",context)
def bnr(request):
    if request.user.is_anonymous:
        return redirect('/login')
    if request.method =='POST':
         username=request.POST.get('username')
         book_name=request.POST.get('book_name')
         author_name=request.POST.get('author_name')
         Mode=request.POST.get('mode')
         if Mode=="Borrow":
            book.objects.filter(BookName=book_name,AuthorName=author_name).update(status="unavailable")
            book.objects.filter(BookName=book_name,AuthorName=author_name).update(BorrowDate=datetime.today(),DueDate=datetime.now() + timedelta(days=7),Username=username)
            
         else:
            book.objects.filter(BookName=book_name,AuthorName=author_name).update(mode="return",ReturnDate=datetime.now(),status="available",Username="---")
    data=book.objects.filter(Username=request.user)
    books=book.objects.all()
    con={'d':data,'availableBooks':books}
    return render(request,'bnr.html',con)

def base(request):
    return render(request,'base.html')

    