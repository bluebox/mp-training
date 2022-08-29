from django.urls import path
from . import views

urlpatterns = [
    path('1/', views.templates_page, name='1')

]