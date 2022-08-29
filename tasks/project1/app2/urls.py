from django.urls import path
from . import views

urlpatterns=[

    path('temp/', views.index, name="temp"),
    path('temp/ex1/', views.temp_page, name="ex1"),
    path('temp/ex2/', views.temp_page2, name="ex2"),
    path('temp/ex3/', views.temp_page3, name="ex3"),
]