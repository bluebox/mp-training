import json

from django.db.models import Q, Max, Min
from django.forms import model_to_dict
from django.http import JsonResponse, Http404
from django.shortcuts import render, get_object_or_404
from django.views.decorators.csrf import csrf_exempt

from .models import Student

def signup(request):
    if request.method=='POST':
        #to post data used a template and also csrf_token inside it to avoid the csrf not found error
        name=request.POST.get('name')
        age=request.POST.get('age')
        student_class=request.POST.get('class')
        # student=Student(name=name,age=age,student_class=student_class)
        # student.save()
        #using create
        Student.objects.create(name=name,age=age,student_class=student_class)
    return render(request, 'student/signup.html')


def get_by_id(request,pk):
    # using rest client to interact with the endpoint
    if request.method=="GET":
            try:
                student = Student.objects.get(id=pk)
                student = model_to_dict(student)
                return JsonResponse({'student': student})
            except Exception as e:
                raise Http404

            ## instead of this we use
            # student=get_object_or_404(Student,id=pk)
    return JsonResponse({'error':'error'})

def get_data_filter(request,value):
    if request.method=='GET':
        student=list(Student.objects.filter(name=value).values())
        print(student)
        return JsonResponse({'data':student})
    return None

def get_data_exclude(request,age):
    if request.method=="GET":
        student=list(Student.objects.exclude(age=age).values())
        print(student)
        return JsonResponse({'data':student})
    return None

def get_data_ordered_by_asc(request):
    if request.method=='GET':
        student=list(Student.objects.all().order_by('id').values())
        return JsonResponse({'data':student})
    return None

def get_data_ordered_by_desc(request):
    if request.method=='GET':
        student=list(Student.objects.all().order_by('-id').values())
        #student=list(Student.objects.all().order_by('id').reverse().values())
        #also to desc with '-id'
        return JsonResponse({'data':student})
    return None


def Q_data(request):
    if request.method=='GET':
        name,age='anand',22
        a=list(Student.objects.filter(Q(name=name) & Q(age=age)).values())
        b =list( Student.objects.filter(Q(name=name) | Q(age=age)).values())
        c=list(Student.objects.filter(~Q(name=name)).values())
        return JsonResponse({'with name anand and age 22':a,'with name anand or age 22':b,'with name not anand':c})
    return None

def field_lookups(request):
    if request.method=='GET':
        age=18
        a=list(Student.objects.filter(age__gt=18).values())
        b=list(Student.objects.filter(age__gte =18).values())
        c=list(Student.objects.filter(name__istartswith='a').values())
        d=list(Student.objects.filter(name__contains='na').values())
        return JsonResponse({'age greater than 18':a,'age greater that or equal to':b,'name starts with':c,'name contains na':d})
    return None

#for all put and delete request used rest client as the interact with the api
@csrf_exempt
def update_single_row(request):
    if request.method=="PUT":
        data=json.loads(request.body)
        id=data.get('id')
        age=data.get('age')
        student=get_object_or_404(Student,id=id)
        student.age=age
        student.save()
        return JsonResponse({'msg':"updated"})
    return None

@csrf_exempt
def update_multiple_rows(request):
    if request.method=="PUT":
        data=json.loads(request.body)
        from_age=data.get('from_age')
        to_age=data.get('to_age')
        Student.objects.filter(age=from_age).update(age=to_age)
        return JsonResponse({"msg":"success"})
    return None

@csrf_exempt
def delete_single_row(request):
    print("jai shree ram")
    if request.method=="DELETE":
        data=json.loads(request.body)
        id=data.get('id')
        student=get_object_or_404(Student,id=id)
        student.delete()
        return JsonResponse({'msg':'success'})
    return JsonResponse({'err':'error'})

@csrf_exempt
def delete_multiple_rows(request):
    if request.method=='DELETE':
        data=json.loads(request.body)
        age=data.get('age')
        Student.objects.filter(age=age).delete()
        return JsonResponse ({'msg':'sucsess'})
    return JsonResponse({'err':'errorr'})

def aggretaion(request):
    if request.method=='GET':
        from django.db.models import Avg
        avg_age=Student.objects.all().aggregate(avg=Avg("age"))
        max_age=Student.objects.all().aggregate(max=Max('age'))
        min_age=Student.objects.all().aggregate(min=Min('age'))
        return JsonResponse({'avg_age':avg_age,'max_age':max_age,'min_age':min_age})
    return None