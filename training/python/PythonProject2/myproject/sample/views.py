from django.http import HttpResponse
from django.shortcuts import render
context = {
        1 : {
           "title" : "one",
           "context" : "This is going to be more interesting"
        },
        2 : {
            "title": "two",
            "context": "This is going to be more fun"
        },
        3 : {
            "title": "three",
            "context": "This is going to be a low complex"
        },
        4 : {
            "title": "four",
            "context": "No context"
        },
    }
def index(request):
    return render(request,'sample2.html', {"data" : context})

def sample(request):
    return render(request, "sample.html")

def dynamic_url(request, id):
    string = f'''
        <html>
            <head>
                <title>Dynamic</title>
            </head>
            <body>
                <div>
    '''
    if id <= len(context):
        data = context[id]
        string += f'''
                    <h4> {data["title"]} </h4>
                    <p>  {data["context"]} </p>
                   </div>
               </body>
            </html>
        '''
    else:
        string += f'''
                    <p> no content </p>
                    </div>
                </body>
            </html>
            '''
    return HttpResponse(string)