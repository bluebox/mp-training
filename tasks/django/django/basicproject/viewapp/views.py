import json

from django.http import HttpResponse
from django.views.generic import View
from django.views.decorators.http import require_http_methods


# Create your views here.
def index(request):
    if request.method == 'POST':
        return HttpResponse("heloow this is post method")

    elif request.method == 'GET':
        return HttpResponse("heloow this is get method")

    elif request.method == 'DELETE':
        return HttpResponse("heloow this is delete method")

    elif request.method == 'PUT':
        return HttpResponse("heloow this is put method")


@require_http_methods(['GET'])
def decorater(request):
    return HttpResponse("This is get decorator")


@require_http_methods(['POST'])
def decorater1(request):
    return HttpResponse("This is POST decorator")


@require_http_methods(['DELETE'])
def decorater2(request):
    return HttpResponse("This is DELETE decorator")


class index2(View):
    def get(self, request):
        return HttpResponse("this is get method")

    def post(self, request):
        return HttpResponse("this is post method")

    def delete(self, request):
        return HttpResponse("this is delete method")


class index1(View):
    def get(self, request):
        p = request.POST
        x = p.get("color")
        print(x)
        age = p.get("age")
        print(age)

        return HttpResponse("this is get app .hello this is :" + x + " color, my age is: " + str(age))

    def post(self, request):
        p1 = request.GET
        name = p1.get("name")
        print(name)
        gender = p1.get("gender")
        print(gender)
        p2 = request.POST
        name1 = p2.get("name")
        print(name1)
        gender1 = p2.get("gender")
        print(gender1)

        return HttpResponse(
            "MY NAME IS " + name + ": GENDER IS " + gender + "\n " + "MY NAME IS " + str(name1) + ": GENDER IS " + str(
                gender1))


class index4(View):
    def get(self, request):
        p = request.POST
        x = p.get("color")
        print(x)
        age = p.get("age")
        print(age)

        return HttpResponse("this is get app .hello this is :" + x + " color, my age is: " + str(age))

    def post(self, request):
        p1 = request.GET
        name = p1.get("name")
        print(name)
        gender = p1.get("gender")
        print(gender)
        p2 = request.POST
        name1 = p2.get("name")
        print(name1)
        gender1 = p2.get("gender")
        print(gender1)

        return HttpResponse(
            "MY NAME IS " + name + ": GENDER IS " + gender + "\n " + "MY NAME IS " + str(name1) + ": GENDER IS " + str(
                gender1))


class index3(View):
    def get(self, request):
        dict = {"age": 25}
        return HttpResponse(json.dumps(dict), content_type="application/json")
    def post(self, request):
        dict = {"age": 28}
        return HttpResponse(json.dumps(dict))