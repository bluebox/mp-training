# from urllib import request
from django.shortcuts import render

from .models import variables
# Create your views here.
def variables1(request):
    data = variables.objects.all().values()
    context = {
        'data1': data,
    }
    return render(request,'variables.html',context)

