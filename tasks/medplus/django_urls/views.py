from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def one_para(request,para):
    return HttpResponse(para)

def two_para(request,para1,para2):
    return HttpResponse(para1+para2)
def regex_para(request,para):
    return HttpResponse(para)
def uuid_para(request,para):
    return HttpResponse('uuid para'+para)

def nested(request,para):
    return HttpResponse('nested para'+para)

def slug_para(request,para):
    return HttpResponse("slug para"+para)