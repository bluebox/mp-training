from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.

def twoparameter(request, parameter1,parameter2):
    html=""
    html= "this is my app " + str(parameter1) + "" + str(parameter2)
    print(parameter1)
    return HttpResponse(html)

# passing extra options from url to view to stop conflict
def oneparameter(request, parameter1):
    html = ""
    html = "this is my app " + str(parameter1)
    print(parameter1)
    return HttpResponse(html)

def intparameter(request, parameter):
    html = ""
    html = "this is my app " + str(parameter)
    print(parameter)
    return HttpResponse(html)
def uuidparameter(request,parameter):
    html = ""
    html = "this is my app " + str(parameter)
    print(parameter)
    return HttpResponse(html)

def slugparameter(request,parameter):
    html = ""
    html = "this is my app " + str(parameter)
    print(parameter)
    return HttpResponse(html)

