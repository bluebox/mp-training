from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.
def index(request):
    html='this is index page'
    return HttpResponse(html)

def para1(request,param):
    html = 'this is para1 page'+str(param)
    return HttpResponse(html)

def paraint(request,integer):
    html = 'this is para int page'+ str(integer)
    return HttpResponse(html)
def paraslug(request,slug):
    html = 'this is a slug page'+str(slug)
    return HttpResponse(html)

def custom(request,cust):
    html = 'thsi is customised converter'+str(cust)
    return HttpResponse(html)

def regex_path(request,parameter):
    html = 'this is a regex page repath '+str(parameter)
    return HttpResponse(html)
def ondate(request,paraa):
    html = 'this is another page'+str(paraa)
    return HttpResponse(html)

def option(request,number):
    html = 'you came to page number'+str(number)
    return HttpResponse(html)

def nestedarg(request,sid,name,branch):
    html = 'given parameters are '+name+str(sid)+branch
    return HttpResponse(html)