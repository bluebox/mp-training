from django.shortcuts import render
from django.http import HttpResponse
from . import template
from tasks.django_learning.modelapp.models import Customer


def log(request):
    # return render(request, 'templates/login.html')
    return HttpResponse("uyhedfjhn")

# Create your views here.
def login(request):
    if request.method == "POST":
        obj1 = Customer()
        obj1.mail = request.POST['mail']
        obj1.password = request.POST['pass']
        obj1.save()
    return HttpResponse("successful")
