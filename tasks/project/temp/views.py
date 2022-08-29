from django.shortcuts import render


# Create your views here.
def firsttemp(request):
    return render(request, 'index.html')


def temp2(request, id, s, sl):
    context = {
        'id': id,
        's': s,
        'sl': sl,
        'lis':[1,1,1,1,1,None]
    }
    return render(request, 'index1.html', context)

def temp3(request):
    return render(request, 'index2.html',{"sent":"hi their iam srikar can i know your name"})