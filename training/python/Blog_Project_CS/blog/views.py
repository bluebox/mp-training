from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.
def UI_builder(request):
	return HttpResponse('<h1>I had a blast working in python</h1>')

