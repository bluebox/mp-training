from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def index(request):
     return  render(request,'temp/index.html',{'name':'shashi'}) #variables
def tags(request):
     return render(request,'temp/tags.html',{'a':1,'players':['virat','sachin']})
def filters(request):
     return render(request,'temp/filters.html',{'a' : 2})

