from django.shortcuts import render
from django.http import HttpResponse
import json
from django.views import View
# Create your views here.

class NewView(View):
    def get(self, request):
        para = request.GET
        print(para.get('name'))
        response_object = {
            "message" : "This is get method",
            "para":  para
        }
        return HttpResponse(json.dumps(response_object), content_type = "application/json")

    def post(self, request):
        para = request.GET
        print(para.get('name'))
        response_object1 = {
            "message": "This is get method",
            "para": para
        }
        para1 = request.POST
        print(para1.get('name'))
        response_object1 = {
            "para": para
        }
        return HttpResponse(json.dumps(response_object1), content_type="application/json")