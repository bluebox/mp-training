from django.shortcuts import render

# Create your views here.
def index(request):
    context = {'name': 'irfan'}
    return render(request, 'myapp2/index.html', context)

def datavariable(request):

    context={'name':'Irfan','age':25,'fav':['Harry Potter','Cricket','Table tennis'],'data1':[1,2,3,4,5,6,7]}
    return render(request,'myapp2/index.html',context)


def menu(request):
    return render(request,'myapp2/home.html')
