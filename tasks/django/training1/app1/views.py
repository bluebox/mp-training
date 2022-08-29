from django.shortcuts import render
from django.http import HttpResponse

def par1(request):
    html="this is Rakesh"
    return HttpResponse(html)

def par2(request,parameter):
    html="this is uuid" + " " + str(parameter)
    return HttpResponse(html)

def par3(request,parameter1,parameter2):
    html="this is multiple" + str(parameter1) + str(parameter2)
    return HttpResponse(html)

def par4(request,parameter):
    html="this is slug" + " " + str(parameter)
    return HttpResponse(html)

def par5(request,parameter):
    html="this is regex" + " " + str(parameter)
    return HttpResponse(html)

def par6(request,parameter):
    html="this is repath" + " " + str(parameter)
    return HttpResponse(html)

def par7(request,parameter):
    html="this is extra options" + " " + str(parameter)
    return HttpResponse(html)

