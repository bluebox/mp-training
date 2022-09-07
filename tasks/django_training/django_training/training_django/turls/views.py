from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.
def calls(request):
    return HttpResponse("hello nikas")
def callintparam(request,param1):
    return HttpResponse("coming int {{param1}}" + str(param1))
def convertor_param(request,param1):
    return HttpResponse("coming on converter parameter" + str(param1))


def callstrparam(request,param1):
    return HttpResponse("coming String {{param1}}" + str(param1))


def slagintparam(request,param1):
    return HttpResponse("coming slug {{param1}}" + str(param1))

def callpathparam(request,param1):
    return HttpResponse("coming path {{param1}}" + str(param1))

def callrpath(request,rpath):
    return HttpResponse("coming re-path {{param1}}" + str(rpath))


def callgudhu(request,extraoption):
    return HttpResponse(extraoption)
    # return None