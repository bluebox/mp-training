from django.template import loader
from django.http import HttpResponse


def index(request):
    template = loader.get_template('myfirst.html')
    return HttpResponse(template.render())


