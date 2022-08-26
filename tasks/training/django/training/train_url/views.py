from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.

def homePage(request):
    response = 'this is home page'
    return HttpResponse(response)

def intPage(request,val):
    response = 'this is integer page: ' + str(val)
    print(type(val))
    return HttpResponse(response)

def stringPage(request,val):
    response = 'this is string page: ' + val
    print(type(val))
    return HttpResponse(response)

def uuidPage(request,val):
    response = 'this is uuid page: ' + str(val)
    print(type(val))
    return HttpResponse(response)


def slugPage(request,val):
    response = 'this is slug page: ' + str(val)
    print(type(val))
    return HttpResponse(response)


def converterPage(request,val):
    response = 'this is converter page: ' + str(val)
    print(type(val))
    return HttpResponse(response)

def repathPage(request,val):
    response = 'this is repath page: ' + str(val)
    print(type(val))
    return HttpResponse(response)

def nestedArgPage(request,name,age,place):
    response = 'this is nested arguments  page: ' + 'args: ' + str(name) +", " + str(age)  +", " + str(place)
    return HttpResponse(response)


def extraOptionsPage(request,**dict):
    response = 'this is extra arguments page: ' + 'args: ' + str(dict["name"]) +", " + str(dict["age"])  +", " + str(dict["place"])
    return HttpResponse(response)