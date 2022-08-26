from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.


def noparams(request):
    return HttpResponse("this is the page with no parameters")

def one_param(request,params):
    str1="the parameter you passed is "+str(params)
    return  HttpResponse(str1)

def int_param(request,params):
    str1="the parameter you passed is "+str(params)
    return  HttpResponse(str1)

def two_param(request,param1,param2):
    str1="the parameter you passed is "+str(param1)+str(param2)
    return  HttpResponse(str1)

def validate(request,params):
    str1="the parameter you passed is "+str(params)
    return  HttpResponse(str1)