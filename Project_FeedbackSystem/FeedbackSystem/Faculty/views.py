from django.shortcuts import render
from django.http import HttpResponse
from .models import faculty_details

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
        experience = request.POST['experience']
        mainsubject = request.POST['mainsubject']
        qualification = request.POST['qualification']
        department = request.POST['department']
        # usertype = request.POST['usertype']

        data = faculty_details(first_name = fname, last_name = lname, facul_username = username, password = password, gender = gender, date_of_birth = dateofbirth, joining_date = joiningdate, experience = experience, main_subject = mainsubject, qualification = qualification, department = department, user_type = "Faculty")
        data.save()
        return render(request, 'Faculty/AddNewFaculty.html')
    else:
        return HttpResponse("Record NOT Saved Successfully")