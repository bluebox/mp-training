from django.urls import path,register_converter,re_path
from . import views
urlpatterns = [
    path('fun/', views.fun),
    path('fun2/', views.post1),
    path('class1/',views.ClassView.as_view())
    ]