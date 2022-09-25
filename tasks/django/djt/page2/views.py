from django.shortcuts import render

# Create your views here.
def temp(request):
    context = {
        "name": "venky",
        "age" : 22,
        "profession" : "Associative software engineer",
        "company" : "Med Plus",
        "count" :0,
    }
    return render(request, "page2/temp1.html", context)
