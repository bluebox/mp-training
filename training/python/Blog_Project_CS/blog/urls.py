from django.urls import path
from . import views
urlpatterns=[
    path('ui/',views.UI_builder)

]
