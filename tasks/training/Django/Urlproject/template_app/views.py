from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.
def template1(request):
    return HttpResponse("<h1> This is without template page <h1>" )


def template2(request):
    return render(request,"page1.html")
    # return HttpResponse("<h1> This is without template page <h1>" )


def context(request):
    con={'name':'Dhanush'}
    lis=['name','age','height','weight']
    return render(request, "context.html", {'con':con, 'lis':lis})

def directing(request):
    return render(request, "sample.html" )


def filter(request):
    con={"name":"Dhanush"}
    return render(request,"filter.html",con)


def conditions(request):
    lis=[1,2,3,4,5,6]
    return render(request,"conditions.html", {"lis":lis})