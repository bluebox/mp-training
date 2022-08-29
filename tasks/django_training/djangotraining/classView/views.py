from django.http import HttpResponse
from django.shortcuts import render
from django.views import View
import json

# Create your views here.
class FirstView(View):
    def get(self,request):
        return HttpResponse("THIS IS THE GET METHOD")

    def post(self,request):
        return HttpResponse("THIS IS THE POST METHOD")


class WithParams(View):
    def get(self,request):
        params=request.GET
        value=params.get('color')
        return HttpResponse("THIS IS THE GET METHOD and with parameter color : " + value)

class WithFormData(View):
    def get(self, request):
        params = request.GET
        value = params.get('color')
        return HttpResponse("THIS IS THE GET METHOD and with parameter color : " + value)

    def post(self,request):
        params=request.GET
        form_data=request.POST

        value=params.get('color')
        first_name=form_data.get('first_name')
        last_name=form_data.get('last_name')

        return HttpResponse("THIS IS THE POST METHOD and with parameter color : " + value + "  and form_data first_name : "+first_name
                            + " and last_name : "+last_name)


class WithJsonResponse(View):
    def get(self,request):
        response_object={
            'name':'django',
            'course':'230',
            'date':'2018-05-01'

        }
        return HttpResponse(json.dumps(response_object),content_type='application/json')