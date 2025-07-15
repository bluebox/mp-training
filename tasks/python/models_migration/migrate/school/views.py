from django.shortcuts import render
from .models import *
from django.db.models import *

def students_per_class(request):
    if request.method == 'GET':
        qs = Student.objects.values('Class_id').annotate(student_count=Count('Class_id'))
        print(qs)

def average_grade_per_class(request):
    if request.method == 'GET':
        qs = Results.objects.values('Class').annotate(avg_grade = Avg('grade'))
        print(qs)

def students_top_list(request):
    if request.method == 'GET':
        qs = Results.objects.values('student__name','Class','subject__Name','percentage').filter(percentage__gt=90)
        print(qs)

def teacher_no_subjects(request):
    if request.method == 'GET':
        qs = Subject.objects.values('Teacher__Name').annotate(Subject_count=Count('Name'))

def class_topper(request):
    if request.method == 'GET':
        qs = 0