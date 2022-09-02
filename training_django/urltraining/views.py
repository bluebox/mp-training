from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.


def home(request):
    return HttpResponse('hello everyone this is homepage of url training')


def parameter(request,param):
    data=param
    return HttpResponse('responding with parameter value '+str(data))


def parameter_int(request,param):
    data=param
    return HttpResponse('responding with integer parameter value '+str(data))


def parameter_str(request,param):
    data=param
    return HttpResponse('responding with string parameter value '+data)


def parameter_slug(request,param):
    data=param
    return HttpResponse('responding with slug parameter value '+str(data))


def parameter_uuid(request,param):
    data=param
    return HttpResponse('responding with uuid parameter value '+str(data))


def parameter_reg(request,param):
    data=param
    return HttpResponse('responding with regular expression value '+str(data))


def parameter_regnest(request,param):
    data=param
    return HttpResponse('responding with nested regular expression value '+str(data))