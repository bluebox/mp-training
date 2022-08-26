from django.shortcuts import render
from django.http import HttpResponse


# Create your views here.
def home(request, para, arg):
    html = 'Hello' + str(para) + str(arg)
    return HttpResponse(html)


def name1(request, nm):
    html = "<h1>Hi..!</h1> " + nm
    return HttpResponse(html)


def int_para(request, a):
    html = "Integer parameter " + a
    return HttpResponse(html)


def str_para(request, s):
    html = "String parameter " + s
    return HttpResponse(html)


def confirm_year(request, year):
    return HttpResponse("this is " + str(year))
