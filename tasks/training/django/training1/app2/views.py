from django.shortcuts import render
from django.http import HttpResponse


def homePage(request):
    return render(request, 'app2/home.html')


def passContext(request):
    context = {
        'Topic': "Cricket",
        'Player_details': [{"name": "Virat Kohli", "country": "India", "type": "Batsman"},
                 {"name": "Nicholas Pooran", "country": "West Indies", "type": "Wk-Batsman"},
                 {"name": "Kagiso Rabada", "country": "SouthAfrica", "type": "Fast-Bowler"},
                 {"name": "Kane Willamson", "country": "Newzealand", "type": "Batsman"},
                 {"name": "Ben Stokes", "country": "England", "type": "All-rounder"},
                 {"name": "Rashid Khan", "country": "Afghanistan", "type": "Leg spinner"},

                 ],
        "sentence" : "Plays Cricket"
    }
    return render(request, 'app2/temp.html', context)