from django.shortcuts import render

# Create your views here.
from django.http import HttpResponse
from django.template import loader


def home(request):
    # return HttpResponse("Welcome to my Django app!")
    template = loader.get_template('first.html')
    return HttpResponse(template.render())