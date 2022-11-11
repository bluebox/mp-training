from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.


def default(request):
    html = "day 1:default page"
    return HttpResponse(html)


def withparams(request, parameter):
    html = "day 1:single parameter"+str(parameter)
    return HttpResponse(html)


def withparams2(request, parameter1, parameter2):
    parameter=parameter1+parameter2
    html = "day 1:multiple parameter"+str(parameter)
    return HttpResponse(html)


def withparams3(request, parameter):
    html = "day 1:single slug parameter"+str(parameter)
    return HttpResponse(html)


def withparams4(request, parameter):
    html = "day 1:single uuid parameter"+str(parameter)
    return HttpResponse(html)


def custom(request, param):
    html = "day 1:custom parameter"+str(param)
    return HttpResponse(html)


def regex(request, parameter):
    html = "day 1:regex parameter"+str(parameter)
    return HttpResponse(html)


def nest(request, parameter):
    html = "day 1:nested parameter" + str(parameter)
    return HttpResponse(html)


def extra(request, parameter, name):
    html = "day 1:extra parameter" + str(parameter)+name
    return HttpResponse(html)
