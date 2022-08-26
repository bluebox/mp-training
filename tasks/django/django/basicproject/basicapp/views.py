from django.shortcuts import render
from django.http import HttpResponse


# Create your views here.
def index(request):
    http = "thia ia shyam"
    return HttpResponse(http)


def home(request):
    http = "thia ia shyam"
    return HttpResponse(http)


def parameter1(request, parameter):
    print(parameter)
    print(type(parameter))
    http = "this is   parameter " + str(parameter)
    return HttpResponse(http)


def int_parameter(request, parameter):
    print(parameter)
    print(type(parameter))
    http = "this is  int parameter " + str(parameter)
    return HttpResponse(http)


def nested_int_parameter(request, parameter1, parameter2, parameter3):
    print(parameter1, parameter2, parameter3)
    print(type(parameter1))
    print(type(parameter2))
    print(type(parameter3))

    http = "year: " + str(parameter1) + " " + "month: " + str(parameter2) + " " + "date: " + str(parameter3)
    return HttpResponse(http)


def regex_parameter(request, parameter):
    print(parameter)
    print(type(parameter))
    http = "this is  regex parameter " + str(parameter)
    return HttpResponse(http)


def repath(request, name1):
    print(name1)
    print(type(name1))
    http = "this is  regex nested parameter " + str(name1)
    return HttpResponse(http)

def nested_repath(request, name1,name2,name3):
    print(name1)
    print(type(name1))
    http = "year: " + str(name1) + " " + "month: " + str(name2) + " " + "date: " + str(name3)
    return HttpResponse(http)
def comments(request, number):
    print(number)
    print(type(number))
    http = "this is  regex nested parameter " + str(number)
    return HttpResponse(http)
