from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def home(request):
    return render(request, 'home.html' )

def customer_login(request):
    return render(request, 'customer_login.html')

def owner_login(request):
    return render(request, 'owner_login.html')