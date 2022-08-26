from django.shortcuts import render,redirect
import uuid
from django.http import HttpResponse
from .models import urls
# Create your views here.
def index(request):
    return render(request,'index.html')

def create(request):
    if request.method=='POST':
        link1 = request.POST['link']
        uid = str(uuid.uuid4())[:5]
        new_url = urls(link=link1,uuid=uid)
        new_url.save()
        return HttpResponse(uid)


def go(request,pk):
    url_details = urls.objects.get(uuid=pk)
    return redirect('https://'+url_details.link)