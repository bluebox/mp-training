from timeit import repeat
from unicodedata import name
from django.urls import path,register_converter,re_path
from .views import homePage,intPage,stringPage,uuidPage,slugPage,converterPage,repathPage,nestedArgPage, extraArgPage
from .converter import StudentUsnConverter
# register converter
register_converter(StudentUsnConverter , 'usn')
urlpatterns=[
    path('' , homePage , name='home'),
    path('int-page/<int:val>/' , intPage , name='int-page'),
    path('str-page/<val>/' , stringPage , name='string-page'),
    path('uuid-page/<uuid:val>/' , uuidPage , name='uuid-page'),
    path('slug-page/<slug:val>/' , slugPage, name='slug-page'),

    # custom converter type 
    path('converter-page/<usn:val>/',converterPage,name='converter-page'),

    # re_path
    re_path(r'^repath-page/(?P<val>[A-Z]{4})/$',repathPage,name='re-path'),

    # nested arguments
    path('nested-args/<str:name>/<int:age>/<str:place>/',nestedArgPage , name="nested-page"),

    # passing extra arguments
    path("extra-args/",extraArgPage , {'name':"Aish",'age':21,'place':'bangalore'} , name="extra-args")
]
