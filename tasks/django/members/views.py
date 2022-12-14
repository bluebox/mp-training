from django.http import HttpResponse, HttpResponseRedirect
from django.template import loader
from .models import *
from .forms import StudentForm
from django.urls import reverse
from django.shortcuts import render
from .filters import BranchFilter
# from django.db import connection


def change_function():
  mymembers = list(StudentInfo.objects.all())
  for i in mymembers:
    abc=i.image
    abc=abc.decode()
    i.image_strl=abc
  StudentInfo.objects.bulk_update(mymembers, fields=["image_strl"])


def index(request):
  change_function()
  mymembers = StudentInfo.objects.select_related('branch').all()
  myfilter = BranchFilter(request.GET, queryset=mymembers)
  mymembers = myfilter.qs
  template = loader.get_template('index.html')
  context = {
    'students': mymembers,
    'myfilter': myfilter,
  }
  return HttpResponse(template.render(context, request))

def view_student(request, id):
  return HttpResponseRedirect(reverse('index'))

def add(request):
  if request.method == 'POST':
    form = StudentForm(request.POST)
    if form.is_valid():
      new_student_id = form.cleaned_data['student_id']
      new_student_name = form.cleaned_data['student_name']
      new_roll_no = form.cleaned_data['roll_no']
      new_branch_id = form.cleaned_data['branch']
      new_dob = form.cleaned_data['dob']
      new_address = form.cleaned_data['address']
      new_gender = form.cleaned_data['gender']
      new_phone_no = form.cleaned_data['phone_no']
      new_image = form.cleaned_data['image']

   
      new_student = StudentInfo(
        student_id = new_student_id,
        student_name = new_student_name,
        roll_no = new_roll_no,
        branch = new_branch_id,
        dob = new_dob,
        address = new_address,
        gender = new_gender,
        phone_no = new_phone_no,
        image = new_image
      )
      new_student.save()
      img_obj = form.instance
      return render(request, 'add.html', {
        'form': StudentForm(),
        'success': True,
        'img_obj': img_obj,
      })  
  else:
    form = StudentForm()
  return render(request, 'add.html', {
    'form': StudentForm(),
  })  
      

def edit(request, id):
  if request.method == 'POST':
    student = StudentInfo.objects.get(pk=id)
    form = StudentForm(request.POST, request.FILES, instance=student)
    if form.is_valid():
        form.save()
        img_obj = form.instance
        return render(request, 'edit.html', {
        'form': form,
        'success': True,
        'img_obj': img_obj,
      })  
  else:
    student = StudentInfo.objects.get(pk=id)
    form = StudentForm(instance=student)
  return render(request, 'edit.html', {
    'form': form,
  })   
  

def delete(request, id):
  if request.method=='POST':
    student = StudentInfo.objects.get(pk=id)
    student.delete()
  return HttpResponseRedirect(reverse('index'))

def view_marks(request, id):
    mymember = Marks.objects.filter(student=id)
    context = {
      'marks': mymember,
    }
    return render(request, 'marks.html', context)
    
     
  