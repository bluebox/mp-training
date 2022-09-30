import io

from django.shortcuts import render, redirect
from django.http import HttpResponse, HttpResponseRedirect, JsonResponse
from django.contrib import messages
# from  import FacultySerializer
from Admin.models import faculty_details
from Admin.serializer import FacultySerializer
from rest_framework.renderers import JSONRenderer
from rest_framework.parsers import JSONParser
from django.views.decorators.csrf import csrf_exempt


# Create your views here.

def display_faculty(request):
    allData = faculty_details.objects.all()
    context = {
        'allData' : allData
    }
    return render(request, 'Faculty/DisplayAllFaculty.html', context)

def register_new_faculty(request):
    return render(request, 'Faculty/AddNewFaculty.html')

def add_faculty(request):
    if request.method == 'POST':
        fname = request.POST['fname']
        lname = request.POST['lname']
        username = request.POST['username']
        password = request.POST['password']
        gender = request.POST['gender']
        dateofbirth = request.POST['dateofbirth']
        joiningdate = request.POST['joiningdate']
        experience = int(request.POST['experience'])
        mainsubject = request.POST['mainsubject']
        qualification = request.POST['qualification']
        department = request.POST['department']
        usertype = request.POST['usertype']

        # data = faculty_details.objects.raw('INSERT INTO Faculty.faculty_details (first_name, last_name, facul_username, password, gender, date_of_birth, joining_date, experience, main_subject, qualification, department, user_type) values(fname,lname,username,password,gender,dateofbirth,joiningdate,experience,mainsubject,qualification,department,usertype)')
        data = faculty_details.objects.create(first_name = fname, last_name = lname, facul_username = username, password = password, gender = gender, date_of_birth = dateofbirth, joining_date = joiningdate, experience = experience, main_subject = mainsubject, qualification = qualification, department = department, user_type = usertype)
        data.save()
        # return HttpResponse("<script>alert('Saved Successfully');</script>")
        # messages.info(request, 'Your password has been changed successfully!')
        return render(request, 'Faculty/AddNewFaculty.html')
    else:
        m = request.method

        return HttpResponse("Record NOT Saved Successfully", {'method':m})

@csrf_exempt
def faculty_serializer(request):
    if request.method == 'GET':
        # info = faculty_details.objects.get(facul_username = pk)
        info = faculty_details.objects.all()
        serializer = FacultySerializer(info, many=True)
        json_data = JSONRenderer().render(serializer.data)
        return HttpResponse(json_data, content_type = "application/json")
        # context = {
        #     'json_data': json_data
        # }
        # return render(request, 'Faculty/DisplayAllFaculty.html', context)


@csrf_exempt
def faculty_create(request):
    if request.method == 'POST':
        stream = JSONParser().parse(request)
        newData = FacultySerializer(data=stream)
        if newData.is_valid():
            newData.save()
            return JsonResponse("Record saved successfully", safe=False)
        return JsonResponse("Failed to add record", safe=False)
    # m = request.method
    return HttpResponse("Record NOT Saved Successfully, Method is GET")
