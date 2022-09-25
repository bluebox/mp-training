from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.

def index(request, month):
    html = "hello world " + str(month)
    return HttpResponse(html)

def index2(request, month):
    html = "hello world2 " + str(month)
    return HttpResponse(html)

def index3(request, month):
    html = "hello world2 " + str(month)
    return HttpResponse(html)

def nest(request, month):
    html = "hello world2 " + str(month)
    return HttpResponse(html)
def index4(request, month):
    html = "hello world2 " + str(month)
    return HttpResponse(html)