from django.shortcuts import render
from django.http import HttpResponse

# Create your views here
def index(request):
    return HttpResponse('first page')

def regex(request,pan_card):
    return HttpResponse(pan_card)


def one_parameter(request,para):
    return HttpResponse(para)

def two_parameter(request,para1,para2):
    return HttpResponse(str(para1)+str(para2))

def parameter(request,para1):
    return HttpResponse(para1)

def nested(request,para):
    return HttpResponse(para)


def uuid_para(request,para):
    return HttpResponse(para)