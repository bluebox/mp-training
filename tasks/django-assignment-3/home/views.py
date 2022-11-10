from http.client import HTTPResponse
from django.contrib.auth.decorators import login_required
from django.shortcuts import render, redirect
from django.contrib.auth.models import User
from .models import Blog as blog
from django.contrib.auth.forms import UserCreationForm
from django.contrib import messages
from .forms import UserRegistrationForm

# Create your views here.

@login_required(login_url = 'login')
def homePage(request):
    return render(request, 'home/home.html')

@login_required(login_url = 'login')
def blogPage(request):
    return render(request, 'home/blog.html')

@login_required(login_url = 'login')
def aboutPage(request):
    return render(request, 'home/about.html')

def signupPage(request):

    if (request.method == "POST"):
        form = UserRegistrationForm(request.POST)
        if (form.is_valid()):
            form.save()
            username = form.cleaned_data.get('username')
            message = messages.success(request, f'account successfully created for {username}, you can now login to your account')
            return redirect('login')
    else:
        form = UserRegistrationForm()

    return render(request, 'home/signup.html', {"form":form})

# def loginPage(request):
#     return render(request, 'home/login.html')