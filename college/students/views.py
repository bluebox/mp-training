from django.shortcuts import render
from django.shortcuts import HttpResponse
from . models import *

def studentsHomePage(request):
    students = Student.objects.all()
    context = {
        'students': students
    }
    return render(request, 'students/studentsDetails.html', context)


def studentDetails(request, id):
    studentDetails = Student.objects.get(id=id)
    context = {
        'student': studentDetails,
    }

    return render(request, 'students/studentDetailedPage.html', context)


def filterByBranch(request):
    dic = request.POST
    branch = Branch.objects.get(branch=dic['branch_selected'])
    students_by_branch = Student.objects.filter(branch=branch)
    context = {
        'students': students_by_branch,
    }
    return render(request, 'students/studentsDetails.html', context)


def searchByID(request):
    dic = request.POST
    try:
        student_by_ID = Student.objects.get(id=dic['student_id'])
    except Student.DoesNotExist:
        return render(request, 'students/doesnotExist.html')
    context = {
        'student': student_by_ID,
    }
    return render(request, 'students/studentByID.html', context)


def studentMarks(request, id):
    studentDetails = Student.objects.get(id=id)
    dic = request.POST
    semester_ID = dic['semster_ID']
    sem = Semester.objects.get(semester=semester_ID)
    marks = Marks.objects.filter(student=studentDetails, semester=sem)
    print(marks)
    context = {
        'marks': marks,
    }
    return render(request, 'students/marks.html', context)


def addStudent(request):
    dic = request.POST
    new_branch = Branch.objects.get(id=int(dic['branch']))
    new_student = Student.objects.create(first_name=dic['first'], last_name=dic['last'], date_of_birth=dic['dob'], branch=new_branch)
    studentDetails = Student.objects.all()
    context  = {
        'new_student': new_student,
        'students': studentDetails,
    }
    return render(request, 'students/studentsDetails.html', context)


def renderForm(request):
    return render(request, 'students/addStudent.html')
