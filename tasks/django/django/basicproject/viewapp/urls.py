from django.urls import path

from .views import index, index1, index2,decorater,decorater1,decorater2,index3

urlpatterns = [
    path('vie', index),
    path('', index1.as_view()),
    path('home', index2.as_view()),
    path('deco', decorater),
    path('deco1', decorater1),
    path('deco2', decorater2),
    path('json', index3.as_view()),

]
