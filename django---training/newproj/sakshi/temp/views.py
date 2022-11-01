from django.shortcuts import render
from django.http import HttpResponse
from django.template import loader


# Create your views here.
def temp1(request):
    html = 'hi'
    return HttpResponse(html)


# Create your views here.
def testing(request):
    template=loader.get_template('temp/index.html')
    context = {'name': 'Sakshi', 'age': 26, 'fav': ['books', 'sleep', 'anime','Coffee'], 'list1': ['monday', 'tuesday', 'wednessday', 'thursday', 'friday', 'saturday', 'sunday']}
    return HttpResponse(template.render(context,request))
    
