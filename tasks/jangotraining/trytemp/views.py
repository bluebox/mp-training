from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.
def temp1(request):
    data={'list1':[1,2,3,4,5,6],'var1':'hello'}
    return render(request,'trytemp/index.html',data)
def filters(request):
    data={'name':'samba','age':21,'course':'engineering','place':'anantapur','list1':[1,2,3,4,5,6],'slug':'this is a slug'}
    return render(request,'trytemp/filter.html',data)