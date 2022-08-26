from django.shortcuts import render
from django.http import HttpResponse


# Create your views here.
def no_param(request):
    html = 'this is a paragraph'
    return HttpResponse(html)

def one_param(request, para):
    html="One param page" + " " +str(para)
    return HttpResponse(html)

def two_param(request, para, para1):
    html="Two param page" + " " +str(para) + " " +str(para1)
    return HttpResponse(html)

def int_param(request, para):
    html="int param page" + " " +str(para)
    return HttpResponse(html)

def slug_param(request, para):
    html="askljbck" + str(para)
    return HttpResponse(html)

def uuid_param(request, para):
    html = para
    return HttpResponse(html)

def id_validation(request, para):
    id = "ID= "+str(para)
    return HttpResponse(id)


def re_path(request, para):
    id = str(para)
    return HttpResponse(id)

def nested(request, para2):
    return HttpResponse(str(para2))

def year_archive(request, year, **kwargs):
    return HttpResponse(str(year)+kwargs['y'])