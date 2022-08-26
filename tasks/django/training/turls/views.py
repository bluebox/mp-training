from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.

def index(request):
    return HttpResponse("Welcome to Urls")

def one_param(request, argument):
    return HttpResponse("this is an argument "+argument)

def two_param(request, arg1,arg2):
    return HttpResponse("This is first arg "+arg1+"\n This is second arg "+str(arg2))

def slug_param(request, slug):
    return HttpResponse("This is a slug param "+slug)

def regex_param(request,reg):
    return HttpResponse("This is a Regex Pattern "+reg)

def regcon(request, reg1):
    return HttpResponse("This is a Regex Converter "+reg1)


def ExtraOptions(request, arg1, arg2):
    return HttpResponse("The User input arg is: "+arg1+" and The extra argument is: "+arg2)


def regex_nest_param(request,arg1):
    return HttpResponse("This is a nested regex: "+arg1)