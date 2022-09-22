from email import contentmanager
from django.shortcuts import render,HttpResponse

# Create your views here.
def homepage(request):
    context={"var_a":10,"name":"yatin","list_a":[5,1,2,3,2,3,4,5,9],"dict_a":{"yatin":"bca","dict_list":[10,20,30]},"string_a":"Hello i am a string","num":3}
    return render(request,"templatetest/index.html",context)

def secondpage(request):
    return HttpResponse('hello i am second page')