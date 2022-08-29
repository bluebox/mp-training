from django.shortcuts import render
from django.http import HttpResponse


# Create your views here.
def home(request):
    return HttpResponse('hi')


def login2(request, id, s, sl, ui, n):
    temp = 'hi ' + str(id) + str(s) + str(sl) + str(ui) + str(n)
    return HttpResponse(temp)


def year_archive(request, year):
    temp = 'year= ' + str(year)
    return HttpResponse(temp)


def month_archive(request, year, month):
    temp = 'year ' + str(year) + 'month' + str(month)
    return HttpResponse(temp)


def article_detail(request, year, month, slug):
    temp = 'year ' + str(year) + 'month' + str(month) + 'slug' + str(slug)
    return HttpResponse(temp)


def comments(request, page_number):
    temp = page_number
    return HttpResponse(temp)


def year_archive(request, year, **kwargs):
    return HttpResponse("<div style=\"color:"+kwargs['color']+"\">"+str(year)+kwargs['t']+'</div>')

