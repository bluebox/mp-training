from django.shortcuts import render

# Create your views here.

def base(request):

    return render(request, 'assignment/base.html')

def variables(request):

    context = {
        'fname': 'sairam',
        'lname': 'yadhav',
        'age': 21
    }

    return render(request, 'assignment/variables.html', context)

def tags(request):

    context = {
        'fname': 'sairam',
        'lname': 'yadhav',
        'age': 21,
        'hobbies': ['playing cricket', 'coding', 'dark comedy and scifi movies', 'roaming']
    }

    return render(request, 'assignment/tags.html', context)

def filters(request):

    context = {
        'fname': 'sairam',
        'lname': 'yadhav',
        'age': 21,
        'hobbies': ['playing cricket', 'coding', 'dark comedy and scifi movies', 'roaming'],
        'slugg': 'this is slugified'
    }

    return render(request, 'assignment/filters.html', context)