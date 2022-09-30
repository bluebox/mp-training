from django.shortcuts import render
from django.http import HttpResponse
from django.template import loader

def index1(requeest):
    template=loader.get_template("index1.html")
    return HttpResponse(template.render())
