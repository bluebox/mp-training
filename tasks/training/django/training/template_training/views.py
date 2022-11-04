from django.shortcuts import render

# Create your views here.


def homePage(request):
    return render(request, 'template_training/home.html')


def passContext(request):
    context = {
        'mentor': "Dheeraj",
        'team': [{"name": "Aishwarya", "state": "Karnataka", "gender": "female"},
                 {"name": "Manasa", "state": "Telangana", "gender": "female"},
                 {"name": "Rakesh", "state": "Telangana", "gender": "male"},
                 {"name": "venkatesh", "state": "Telangana", "gender": "male"},
                 {"name": "Arun", "state": "Andhra Pradesh", "gender": "male"},
                 {"name": "Shazwan", "state": "U.P", "gender": "male"},
                 ],
                 "sentence" : "works at medplus"
    }
    return render(request, 'template_training/team.html', context)
