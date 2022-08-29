
from django.urls import path
from . import views


urlpatterns = [
    path('',views.index,name="home"),
    path('tags',views.tags,name="tags"),
    path('filters',views.filters)
]
