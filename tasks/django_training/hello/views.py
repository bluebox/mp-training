from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.


def no_parameter(request):
    http = ' this is  no parameter page '
    return HttpResponse(http)


def int_parameter(request, parameter):
    http = ' this is int path converter page ' + str(parameter)
    return HttpResponse(http)


def str_parameter(request, parameter):
    http = ' this is  str path converter page ' + str(parameter)
    return HttpResponse(http)


def slug_parameter(request, parameter):
    http = ' this is  slug path converter page ' + str(parameter)
    return HttpResponse(http)


def uuid_parameter(request, parameter):
    http = ' this is  uuid path converter page ' + str(parameter)
    return HttpResponse(http)


def converter_parameter(request, parameter):
    http = ' this is  custom path converter page ' + str(parameter)
    return HttpResponse(http)


def r_parameter(request, parameter):
    http = ' this is  regex rpath page ' + str(parameter)
    return HttpResponse(http)


def two_parameter(request, parameter, parameter1):
    http = ' this is two parameter page parameter1: ' + str(parameter) + " parameter2: " + str(parameter1)
    return HttpResponse(http)


def nested_arguments(request, parameters):
    http = ' this is nested arguments page  ' + str(parameters)
    return HttpResponse(http)


def extra_args(request, **dict):

    http = 'this is passing extra arguments to link '+dict["name"]+" "+dict["place"]
    return HttpResponse(http)

