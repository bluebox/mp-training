from django.http import HttpResponse
from django.shortcuts import render
from . import templates

# Create your views here.

def defaultview(request):

    context={'variable': "sahithi",'values':[2,3,4,6,7,9],'data':45,'name':"Medplus"}
    return render(request, 'variable.html',context)


def examplesview(request):
    return render(request, 'example.html', )


# test commit