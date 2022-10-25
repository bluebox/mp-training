import io

from django.shortcuts import render, redirect
from django.http import HttpResponse, HttpResponseRedirect, JsonResponse
from django.contrib import messages
from Admin.models import faculty_details
from .serializer import FacultySerializer
from Admin.models import subject_details
from Admin.models import department_details
from .serializer import SubjectSerializer
from .serializer import DepartmentSerializer
from rest_framework.renderers import JSONRenderer
from rest_framework.parsers import JSONParser
from django.views.decorators.csrf import csrf_exempt


# Create your views here.

# def display_faculty(request):
#     allData = faculty_details.objects.all()
#     context = {
#         'allData' : allData
#     }
#     return render(request, 'Faculty/DisplayAllFaculty.html', context)

# def register_new_faculty(request):
#     return render(request, 'Faculty/AddNewFaculty.html')

@csrf_exempt
def create_faculty(request):
    if request.method == "POST":
        stream = JSONParser().parse(request)
        newdata = FacultySerializer(data=stream)
        if newdata.is_valid():
            newdata.save()
            return JsonResponse("Faculty added successfully", safe=False)
        return JsonResponse("Data is not valid", safe=False)
    return HttpResponse("Method is not valid")


@csrf_exempt
def update_faculty(request, pk=None):
    if request.method == "POST":
        if (pk != None):
            stream = JSONParser().parse(request)
            info = faculty_details.objects.get(facul_username=pk)
            newdata = FacultySerializer(info, data=stream)
            if newdata.is_valid():
                newdata.save()
                return JsonResponse("Details updated successfully", safe=False)
            return JsonResponse("Data is not valid", safe=False)
        return JsonResponse("User not found", safe=False)


@csrf_exempt
def display_faculty(request):
    if request.method == 'GET':
        # info = faculty_details.objects.get(facul_username = pk)
        info = faculty_details.objects.all()
        serializer = FacultySerializer(info, many=True)
        json_data = JSONRenderer().render(serializer.data)
        return HttpResponse(json_data, content_type = "application/json")
    return render("Methhod is not valid")


@csrf_exempt
def delete_faculty(request, pk=None):
    if request.method == 'GET':
        if(pk):
            # info = faculty_details.objects.get(stud_username=pk)
            faculty_details.objects.get(facul_username=pk).delete()
            return JsonResponse("Record deleted successfully", safe=False)
        return JsonResponse("Faculty Not found or something invalid", safe=False)
    return JsonResponse("Something is wrong", safe=False)


def all_subjects(request):
    info = subject_details.objects.all()
    serializer = SubjectSerializer(info, many=True)
    json_data = JSONRenderer().render(serializer.data)
    return HttpResponse(json_data, content_type="application/json")


def all_departments(request):
    info = department_details.objects.all()
    serializer = DepartmentSerializer(info, many=True)
    json_data = JSONRenderer().render(serializer.data)
    return HttpResponse(json_data, content_type="application/json")
