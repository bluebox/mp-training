from django.shortcuts import render

def welcome(request):
    return render(request, 'index.html')

# def explore_view(request):
#     return render(request, 'index.html')
