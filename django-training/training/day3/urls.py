from django.urls import path
from day3 import views

urlpatterns = [
    # default page
    path('', views.default),
    path('fbv', views.function_based),
    path('d', views.fbv_using_decorators),
    path('c', views.Class_based.as_view()),
    path('j', views.Cbv_json.as_view()),
]