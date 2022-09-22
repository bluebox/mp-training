from django.shortcuts import render,HttpResponse
from .models import admin_users
# Create your views here.
def test(request):
    a=admin_users.objects.all()
    for i in a:
        print(i.id)
    return HttpResponse("hello")