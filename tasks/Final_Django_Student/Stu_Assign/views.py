import random

from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render
from django.template import loader
from django.urls import reverse
from Stu_Assign.forms import StudentForm
from .models import Subjects, Marks, Student_Info


def stu(request):
    return render(request, 'student.html', {
        'pass_stu': Student_Info.objects.all()
    })


def view_student(request, id):
    student = Student_Info.objects.get(id=id)
    return HttpResponseRedirect(reverse('stu'))


def edit(request, id):
    if request.method == 'POST':
        student = Student_Info.objects.get(pk=id)
        form = StudentForm(request.POST, request.FILES, instance=student)
        if form.is_valid():
            form.save()
            img_object = form.instance
            return render(request, 'edit.html', {
                'form': form,
                'success': True,
                'img_object': img_object,
            })
    else:
        student = Student_Info.objects.get(pk=id)
        form = StudentForm(instance=student)
    return render(request, 'edit.html', {
        'form': form
    })


def list_item(request):
    my_marks = Marks.objects.all()

    template = loader.get_template('list.html')
    pas = {
        'marks': my_marks,
    }
    return HttpResponse(template.render(pas, request))


def add(request):
    context = {}
    if request.method == 'POST':
        form = StudentForm(request.POST, request.FILES)
        if form.is_valid():
            new_student_name = form.cleaned_data['name']
            new_address = form.cleaned_data['address']
            new_d_o_b = form.cleaned_data['d_o_b']
            new_branch = form.cleaned_data['branch']
            new_image = form.cleaned_data['image']
            new_student = Student_Info(
                name=new_student_name,
                address=new_address,
                d_o_b=new_d_o_b,
                branch=new_branch,
                image=new_image
            )
            new_student.save()
            img_obj = form.instance
            return render(request, 'add_stu.html', {
                'form': form,
                'success': True,
                'img_obj': img_obj,
            })
    else:
        form = StudentForm()
    return render(request, 'add_stu.html', {
        'form': form
    })


def image_upload_view(request):
    """Process images uploaded by users"""
    if request.method == 'POST':
        form = StudentForm(request.POST, request.FILES)
        if form.is_valid():
            form.save()
            # Get the current instance object to display in the template
            img_obj = form.instance
            return render(request, 'index.html', {'form': form, 'img_obj': img_obj})
    else:
        form = StudentForm()
    return render(request, 'index.html', {'form': form})


def stu_delete(request, id):
    student = Student_Info.objects.get(id=id)
    student.delete()
    return HttpResponseRedirect(reverse('stu'))


def mar_delete(request, id):
    mark = Marks.objects.get(id=id)
    mark.delete()
    return HttpResponseRedirect(reverse('list_item'))


def add_marks(request):
    template = loader.get_template('add_marks.html')
    return HttpResponse(template.render({}, request))


def add_marks_record(request):
    v = Student_Info.objects.get(id=request.POST['first'])
    w = Subjects.objects.get(id=request.POST['second'])
    y = request.POST['forth']
    z = request.POST['fifth']
    marks = Marks(stu_id=v, sub_id=w, mark=y, semester=z)
    marks.save()
    return HttpResponseRedirect(reverse('list_item'))



def stu_profile(request, id):
    student = Student_Info.objects.get(pk=id)
    marks = Marks.objects.filter(stu_id=id)
    template = loader.get_template('student_info.html')
    context = {
        'x': student,
        'z': marks,
    }
    return HttpResponse(template.render(context, request))


def search_student(request):
    """

    :type request: object
    """
    x = int(request.POST.get('key'))
    obj = Student_Info.objects.get(id=x)
    rImage = ['first', 'second', 'third', 'forth', 'fifth', 'sixth', 'seventh']
    marks = Marks.objects.filter(stu_id=x)
    rand = random.choice(rImage) + '.jpeg'
    template = loader.get_template('student_info.html')
    context = {
        'x': obj,
        'y': rand,
        'z': marks,
    }
    return HttpResponse(template.render(context, request))


def filter_stu(request):
    id = request.POST.get('Branch')
    marks = Marks.objects.filter(br_id=id)
    template = loader.get_template('filter_res.html')
    context = {
        'obj': marks
    }
    return HttpResponse(template.render(context, request))
