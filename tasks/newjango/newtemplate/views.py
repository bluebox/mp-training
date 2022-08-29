from django.shortcuts import render
from django.http import HttpResponse
from django.template import loader

# Create your views here.
def jangofilters(request):
    # template = loader.get_template(temps.fil)
    data ={
        "name": "shyam",
        "designation": "Software Engineer"
    }
    return render(request, 'newtemplate/filters.html', data)

def jangotags(request, para):
    data ={
        "name" : para
    }
    return render(request, 'newtemplate/tags.html', data)

def jangoloops(request):
    context = {

    "car" : [
        {'brand': "Audi",
         "model":"Q5",
         "year": "2020"},

        {'brand': "Benz",
         "model": "AMG GLA 35",
         "year": "2022"}
    ]
    }
    return render(request, 'newtemplate/variables.html', context)