from django.shortcuts import render
from django.http import HttpResponse
from django.template import loader

# Create your views here.
def index(request):
    return HttpResponse("Template")
def home(request):
    template=loader.get_template("home.html")
    return HttpResponse(template.render())
def loop(request):
    return render(request,"loop.html")

def count(request):
    context={"count":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio."}
    return render(request,"count.html",context)

def filters(request):
    context={"count":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio."}
    return render(request,"filters.html",context)

def condition(request,num):
    context={"number":num}
    return render(request,"ifstatement.html",context)