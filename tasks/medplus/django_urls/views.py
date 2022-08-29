from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def one_para(request,para):
    return HttpResponse(para)

def two_para(request,para1,para2):
    return HttpResponse(para1+para2)
def regex_para(request):
    return HttpResponse(para1+para2)
def uuid_para(request,para):
    return HttpResponse()

def nested(request):
    return HttpResponse()