from django.shortcuts import render
from django.http import HttpResponse


# Create your views here.
def o_parameter(request):
    html = '0 parameter view'
    return HttpResponse(html)


def one_parameter(request, a_parameter):
    html = 'the parameter is ' + str(a_parameter)
    return HttpResponse(html)


def user_detail(request, uname):
    html = 'the parameter is ' + uname
    return HttpResponse(html)


def sludge(request, slug):
    html = 'the parameter is ' + slug
    return HttpResponse(html)
def uu(request, id):
    html = 'the parameter is ' + str(id)
    return HttpResponse(html)
def index(request):
    html = 'repath view'
    return HttpResponse(html)