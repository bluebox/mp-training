from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.

def parameter(request):
    html=""
    html= "this is my app "
    return HttpResponse(html)


def twoparameter(request, parameter1,parameter2):
    html=""
    html= "this is my app " + str(parameter1) + "" + str(parameter2)
    print(parameter1)
    return HttpResponse(html)

# def oneparameter(request, parameter3):
#     html = ""
#     html = "this is my app " + str(parameter3)
#     print(parameter3)
#     return HttpResponse(html)

def oneparameter1(request, parameter3,p_id):
    html = ""
    html = "extra " + str(parameter3)+ str(p_id)
    print(parameter3)
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
