from http.client import responses

from django.http import HttpResponse
from django.shortcuts import render
from .forms import ContactForm,RegistrationForm

from django.contrib.auth.forms import UserCreationForm, AuthenticationForm
from django.contrib.auth import authenticate,login

def index(request,):
    # return HttpResponse("Hello!")
    return render(request, 'blog/index.html',)

def post_detail(request, id):
    return HttpResponse(f"Post ID: {id}")


def register(request):
    if request.method == 'POST':
        form = RegistrationForm(request.POST)
        if form.is_valid():
            form.save()
    else:
        form=RegistrationForm()
    return render(request,"blog/registration.html",{'form':form})

def auth_login(request):
    if request.method == 'POST':
        form=AuthenticationForm(request=request,data=request.POST)
        if form.is_valid():
            username=form.cleaned_data['username']
            password=form.cleaned_data['password']
            user=authenticate(username=username,password=password)
            if user:
                login(request,user)
                return  render(request,'blog/index.html')
    else:
        form=AuthenticationForm()
    return render(request,"blog/registration.html",{'form':form})


def contact_view(request):
    if request.method == 'POST':
        form = ContactForm(request.POST)
        if form.is_valid():
            # Process the cleaned data
            name = form.cleaned_data['name']
            email = form.cleaned_data['email']
            message = form.cleaned_data['message']

            # Here you'd typically send an email, save to DB, etc.
            return render(request, 'contact_success.html')
    else:
        form = ContactForm()

    return render(request, 'contact.html', {'form': form})

