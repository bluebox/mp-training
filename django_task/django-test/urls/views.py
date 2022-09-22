from django.shortcuts import render
from django.http import HttpResponse

def homepage(request):
    return HttpResponse("This is a homepage")

def param1(request,param1):
    text="Hello "+str(param1)
    return HttpResponse(text)

def param2(request,param1,param2):
    text="Hello "+str(param1)+" "+str(param2)
    return HttpResponse(text)

def paramint(request,para1):
    text = "Hello " + str(para1)
    return HttpResponse(text)

def paramstr(request,para1,para2):
    text = "Hello there " + str(para1) + " " + str(para2)
    return HttpResponse(text)

def uuidparameter(request,param):
    text = "Hello there " + str(param)
    return HttpResponse(text)

def slugparameter(request,param):
    text = "Hello there " + str(param)
    return HttpResponse(text)

def re_path(request,param):
    text = "Hello there " + str(param)
    return HttpResponse(text)

def pancardextra(request,param,userid):
    text = "Hello there " + str(param) + " " +str(userid)
    return HttpResponse(text)

