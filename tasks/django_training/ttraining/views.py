from django.shortcuts import render

# Create your views here.


def templates_page(request):
    context= {
        'name': 'harsha',
        'age': 24,
        'skill': ['html', 'css', 'js', 'python', 'bootstrap', 'djnago'],
        'word_count':"find the number of words in this sentence"

    }
    return render(request, 'ttraining/index.html', context)
