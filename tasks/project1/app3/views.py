from django.shortcuts import render
from django.http import HttpResponse
from django.views import View
from django.views.decorators.http import require_http_methods, require_POST
from django.views.generic import ListView, DetailView, TemplateView
from .models import Hobbies, Biodata


def index(request):
    return HttpResponse("view page")


class ClassBasedView(View):
    def get(self, request):
        return HttpResponse("this is get method" + request.method)

    def post(self, request):
        return HttpResponse("this is  post method" + request.method)

    def put(self, request):
        return HttpResponse("this is  put method" + request.method)

    def delete(self, request):
        return HttpResponse("this is  delete  method" + request.method)

# listview


class Generic(ListView):
    # model = Biodata
    queryset = Biodata.objects.order_by('email')
    template_name = "v_app3/ex4.html"
    context_object_name = "bio_info"


class Generic2(TemplateView):
    template_name = "v_app3/ex4.html"


class Generic3(DetailView):
    model = Biodata
    context_object_name = "bio_info"
    template_name = "v_app3/ex4.html"

    # def get_context_data(self, **kwargs):
    #     context = super().get_context_data(**kwargs)
    #     context['biodata_list'] = Biodata.objects.all()
    #     return context






