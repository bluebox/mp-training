"""medplus URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.urls import path
from . import views
urlpatterns = [
    path('', views.index,name='index'),
    path('view_all_employee',views.view_all_employee,name="view_all_employee"),
    path('add_an_employee',views.add_an_employee,name="add_an_employee"),
    path('delete_an_employee1',views.delete_an_employee1,name="delete_an_employee1"),
    path('delete_an_employee/<str:email_id>',views.delete_an_employee,name="delete_an_employee"),
    path('filter',views.filter,name="filter"),
]


