from django.shortcuts import render, HttpResponse, HttpResponseRedirect

from home.forms import *
from home.models import Subjects, StudentDetails, Marks, Branch, Semester
from django.urls import reverse
from django.template import loader


def showform(request):
    form = FormBranch(request)
    if form.is_valid():
        form.save()
    else:
        return render(request, 'test.html', {'form': form.validation_errors})

    return render(request, 'test.html')


def test(request):
    return render(request, 'test.html')
# Create your views here.


def index(request):
    students = StudentDetails.objects.all()
    branch = Branch.objects.all()
    context = {
        'students': students,
        'branch': branch,
    }
    return render(request, 'index.html', context)
#


def enter_marks(request, id, dic={}):
    student = StudentDetails.objects.get(id=id)
    subjects = Subjects.objects.filter(branch_id=student.branch_id, sem_id=student.sem_id)
    sub_list = []
    for i in subjects:
        sub_list.append(i.name)

    context = {
        'std': student,
        'sub1': sub_list[0],
        'sub2': sub_list[1],
        'sub3': sub_list[2],
        'sub4': sub_list[3],
    }
    context.update(dic)
    return render(request, 'save_marks.html', context)


def save_marks(request, id):
    student = StudentDetails.objects.get(id=id)
    form = SaveMarks(request)
    if form.is_valid():
        marks_list = [form.sub1, form.sub2, form.sub3, form.sub4]
        subjects = Subjects.objects.filter(branch_id=student.branch_id, sem_id=student.sem_id)
        i = 0
        for x in subjects:
            mark_row = Marks(marks_obtained=marks_list[i], sub_id=x, std_id=student, sem_id=student.sem_id)
            mark_row.save()
            i += 1
        sem = Semester.objects.get(id=student.sem_id.id + 1)
        student.sem_id = sem
        student.save()
        return render(request, 'marks.html')
    return render(request, 'save_marks.html', {'form': form.validation_error})


def marks(request):
    if request.method == 'POST':
        form = StudentValidation(request)
        if form.is_valid():
            return enter_marks(request, int(form.std_id))
        return render(request, 'marks.html', {'form': form.validation_error})
    return render(request, 'marks.html')


def subject(request):
    branches = Branch.objects.all()
    sem = Semester.objects.all()
    context = {
        'branch': branches,
        'sem': sem,
    }
    if request.method == 'POST':
        subject_form = SubjectValidation(request)
        if subject_form.is_valid():
            subject_form.save()
            return render(request, "subject.html", context)
        else:
            context['form'] = subject_form.validation_error
            return render(request, 'subject.html', context)
    return render(request, 'subject.html', context)


def student_detail(request):
    branch_table = Branch.objects.all()
    context = {
        'branch': branch_table
    }
    template = loader.get_template('student_details.html')
    if request.method == "POST":
        form = StudentDetailsValidation(request)
        if form.is_valid():
            form.save()
        else:
            context['form'] = form.validation_error
            return render(request, 'student_details.html', context)
    return HttpResponse(template.render(context, request))


def student_profile(request, id):
    std_detail = StudentDetails.objects.get(id=id)
    s = []
    for i in range(1, std_detail.sem_id.id):
        sem = Semester.objects.get(id=i)
        sub = Subjects.objects.filter(branch_id=std_detail.branch_id, sem_id=sem)
        mark = Marks.objects.filter(std_id=std_detail, sem_id=sem)
        s.append({'sub': sub, 'marks': mark})

    branches = Branch.objects.all()
    template = loader.get_template('student_profile.html')
    context = {
        'x': std_detail,
        'result': s,
        'branch': branches,
    }

    return HttpResponse(template.render(context, request))


def update_student(request, id):
    std_detail = StudentDetails.objects.get(id=id)
    std_detail.name = request.POST.get('name')
    std_detail.phone = request.POST.get('phone')
    std_detail.address = request.POST.get('address')
    std_detail.gender = request.POST.get('gender')
    branch_id = request.POST.get('branch')
    br = Branch.objects.get(id=branch_id)
    std_detail.branch_id = br
    for i in range(1, std_detail.sem_id.id):
        sem = Semester.objects.get(id=i)
        mark_table= Marks.objects.filter(std_id=std_detail, sem_id=sem)
        for j in mark_table:
            sub_mark = request.POST.get(j.sub_id.name)
            j.marks_obtained = sub_mark
            j.save()
    std_detail.save()
    return HttpResponseRedirect(reverse(index))


def delete_student(request, id):
    std = StudentDetails.objects.get(id=id)
    std.delete()
    return HttpResponseRedirect(reverse(index))


def cancel(request):
    return HttpResponseRedirect(reverse(index))


def filter_by_branch(request):
    form = FilterValidation(request, 'branch')
    if form.is_valid():
        std = StudentDetails.objects.filter(branch_id=form.entity)
        branch = Branch.objects.all()
        template = loader.get_template('index.html')
        context = {
            "students": std,
            'branch': branch,

        }
        return HttpResponse(template.render(context, request))
    return render(request, 'index.html', {'form': form})


def std_profile(request):
    form = SearchValidation(request)
    if form.is_valid():
        return student_profile(request, form.std_id)
    else:
        return render(request, 'index.html', {"form": form.validation_error})

