from django.shortcuts import render
from django.http import HttpResponse
from django.views.decorators.http import require_http_methods
from django.views import View
import json
from django.core.exceptions import PermissionDenied


class BookListView(View):
    def my_view(self, request):
        if request.method == 'GET':
            return HttpResponse('result is get', status=405)
        elif request.method == 'POST':
            return HttpResponse('result is post', status=205)

    def books(self, request):
        context = {'name': "harry potter and half blood prince", }
        return HttpResponse(json.dumps(context), content_type="application/json")

    def delete(self, request):
        raise PermissionDenied
