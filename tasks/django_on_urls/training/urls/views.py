from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def index(request):
    return HttpResponse("HI")
def inte(request,parameter):
    return HttpResponse("HI" + str(parameter))
def intex(request,parameter,parameter2):
    return HttpResponse("HI" + parameter + parameter2)
def custompara(request,parameter):
    return HttpResponse("12")
def repath(request,empid):
    return HttpResponse("repath_successfull!!!")
def nested(request,page_number):
    return HttpResponse("done with http response!!!! congoo")
def test(request,name):
    return HttpResponse(name)
def kwaargs(request,**args):
    a = [0,0,0]
    i=0
    for  key,value in args.items():
        a[i] = key
        i=i+1
    return HttpResponse(a)


