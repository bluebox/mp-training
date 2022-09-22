from django.urls import path,include
from . import views

urlpatterns = [
    path('function/', views.funcomp),
    path('class/', views.Classcomp.as_view()),
    path('functionif/', views.funcompifcondition),
    path('status/', views.statuscode),
    path('jsonsend/', views.jsonsend),



    

]
