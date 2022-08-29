from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.

def variables(request):

    context={'name':'Purnima','age':23,'fav':['books','GOT','Coffee and watching series'],'list1':[1,2,3,4,5,6,7],'list2':{'a':'A','b':'B'}}
    return render(request,'home2/index.html',context)


def menu(request):
    return render(request,'home2/menu.html')

