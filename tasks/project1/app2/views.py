import datetime

from django.shortcuts import render
from django.http import HttpResponse


def index(request):
    return HttpResponse("template page")


def temp_page(request):
    return render(request, 't_app2/ex1.html')


def temp_page2(request):
    return render(request, 't_app2/ex2.html', {"page": "this is page 2"})


def temp_page3(request):
    context = {
    'numbers': ["one", "two", "three", "four"],
    'text': "this is is ex for slug",
    'mydate': datetime.datetime.now(),
    }
    return render(request, 't_app2/ex3.html', context)

