from django.urls import path,include
from . import views
urlpatterns = [
    path('teacher_login/', views.homepage,name="homepage"),
 
    
]
