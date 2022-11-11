from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.


def withoutTemplate(request):
    return HttpResponse("THIS IS USING HTTP RESPONSE")


def withTemplate(request):
    return render(request, "home.html")

def withTemplate2(request):
    context={"fname": "nikita", 'lname': "singh", 'l':[1,2,3,4,5],'s':"this is a sample sentence"}
    return render(request, "second.html", context)
