from django.shortcuts import render
from django.http import HttpResponse
from .models import faculty_details

# Create your views here.

def display_faculty(request):
    allData = faculty_details.objects.all()
    context = {
        'allData' : allData
    }
    return render(request, 'Admin/DisplayAllFaculty.html', context)