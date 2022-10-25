from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
# from django.shortcuts import render
from .serializer import StudentSerializer, ClassSerializer
from rest_framework.renderers import JSONRenderer
from rest_framework.parsers import JSONParser
from django.views.decorators.csrf import csrf_exempt
from Admin.models import student_details
from Admin.models import class_details

# from .serializerTest import TestSerializer


# Create your views here.


@csrf_exempt
def create_student(request):
    if request.method == "POST":
        stream = JSONParser().parse(request)
        newdata = StudentSerializer(data=stream)
        if newdata.is_valid():
            newdata.save()
            return JsonResponse("Student added successfully", safe=False)
        return JsonResponse("Data is not valid", safe=False)
    return HttpResponse("Method is not valid")


@csrf_exempt
def update_student(request, pk):
    if request.method == "POST":
        if(pk != None):
            stream = JSONParser().parse(request)
            info = student_details.objects.get(stud_username=pk)
            newdata = StudentSerializer(info, data=stream)
            if newdata.is_valid():
                newdata.save()
                return JsonResponse("Details updated successfully", safe=False)
            return JsonResponse("Data is not valid", safe=False)
        return JsonResponse("User not found", safe=False)


def display_student(request):
    if request.method == 'GET':
        # info = faculty_details.objects.get(facul_username = pk)
        info = student_details.objects.all()
        serializer = StudentSerializer(info, many=True)
        json_data = JSONRenderer().render(serializer.data)
        return HttpResponse(json_data, content_type="application/json")
    return render("Something going wrong")

@csrf_exempt
def search_student(request):
    # if request.method == 'post':
        user_data = JSONParser().parse(request)
        username = student_details.objects.filter(stud_username__contains= user_data['data'])
        fname = student_details.objects.filter(first_name__contains=user_data['data'])
        lname = student_details.objects.filter(last_name__contains=user_data['data'])
        gender = student_details.objects.filter(gender__contains=user_data['data'])
        fatherName = student_details.objects.filter(father_name__contains=user_data['data'])
        rollNo = student_details.objects.filter(roll_no__contains=user_data['data'])
        DOB = student_details.objects.filter(date_of_birth__contains=user_data['data'])
        # classCode = student_details.objects.filter(class_code__contains =user_data['data'])
        userType = student_details.objects.filter(user_type__contains=user_data['data'])
        finalData = username | fname | lname | gender | fatherName | rollNo | DOB | userType

        serializer = StudentSerializer(finalData, many=True)
        json_data = JSONRenderer().render(serializer.data)
        return HttpResponse(json_data, content_type="application/json")
    # return render("Something going wrong")

@csrf_exempt
def delete_student(request, pk=None):
    if request.method == 'GET':
        if(pk):
            # info = student_details.objects.get(stud_username=pk)
            student_details.objects.get(stud_username=pk).delete()
            return JsonResponse("Record deleted successfully", safe=False)
        return JsonResponse("Student Not found or something invalid", safe=False)
    return JsonResponse("Somthing is wrong", safe=False)

@csrf_exempt
def create_class(request):
    if request.method == "POST":
        stream = JSONParser().parse(request)
        newdata = ClassSerializer(data=stream)
        if newdata.is_valid():
            newdata.save()
            return JsonResponse("Class added successfully", safe=False)
        return JsonResponse("Failed to add this record", safe=False)
    return HttpResponse(request.method)


def all_classes(request):
    info = class_details.objects.all()
    serializer = ClassSerializer(info, many=True)
    json_data = JSONRenderer().render(serializer.data)
    return HttpResponse(json_data, content_type="application/json")



# @csrf_exempt
# def test_url(request):
#     if request.method == "POST":
#         stream = JSONParser().parse(request)
#         newdata = TestSerializer(data=stream)
#         if newdata.is_valid():
#             newdata.save()
#             return JsonResponse("Record saved successfully", safe=False)
#         return JsonResponse("Failed to add this record", safe=False)
#     return HttpResponse(request.method)