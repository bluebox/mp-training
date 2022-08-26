from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.


def index(request):
    return HttpResponse("hello world")

def default_converter(request, parameter):
    return HttpResponse("one parameter of default(str) data type " + str(parameter))

def int_converter(request, parameter):
    return HttpResponse("one parameter of int data type " + str(parameter))


def int_two_parameter_converter(request, parameter1, parameter2):
    return HttpResponse("two parameters of int data type " + str(parameter1) + " " + str(parameter2))


def customParameter(request, parameter):
    return HttpResponse("custom parameter with regix([A-Z]/{3/}[0-9]/{6/} " + parameter)


def repath(request, parameter):
    return HttpResponse("custom parameter with regix([A-Z]/{3/}[0-9]/{6/} " + parameter)


def nested(request, parameter):
    return HttpResponse("This is nested HTTP Response")


def test(request, parameter):
    return HttpResponse(parameter)