from django.http import HttpResponse
from django.shortcuts import render

def index(request):
    params = {'name':'template', 'place':'mars'};
    return render(request, 'index.html', params)
    # return HttpResponse("hello")

def about(request):
    return HttpResponse('''<a href="#"><h1>About Me<h1/><a/>''')
def analyze(request):
    djtext = request.GET.get('text', 'default')
    removepunc = request.GET.get('removepunc', 'off')
    print(removepunc)
    punctuations = '''!()-{}[];:'"\,?@#$%^&*_~'''
    analyzed=""
    if(removepunc == "on"):
        for char in djtext:
            if char not in punctuations:
                analyzed+=char
    else:
        analyzed=djtext
    params={'purpose':'Removed Punctuation', 'analyzed_text':analyzed}
    return render(request, 'analyze.html', params)