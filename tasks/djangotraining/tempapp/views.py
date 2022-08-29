from django.shortcuts import render
# from django.http import HttpResponse
# Create your views here.
def HtmlResponse(req):
    return render(req, "tempapp/index.html")

def HtmlResponseWithVariable(req):
    context = {
        "name":"hari",
        'age':22
    }
    return render(req, "tempapp/variables.html", context=context)

def HtmlResponseWithTags(req):
    context = {
        "name":"hari",
        'age':22,
        'data' : [1, 2, 3, 4, 5]
    }
    return render(req, "tempapp/tags.html", context=context)

def HtmlResponseWithFilters(req):
    context = {
        "name":"hari"
    }
    return render(req, "tempapp/filter.html", context=context)