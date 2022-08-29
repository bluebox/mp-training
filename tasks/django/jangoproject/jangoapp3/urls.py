from django.urls import path
from . import views
urlpatterns = [
    path('path1/',views.function_one,name='function_one'),

    path('path2/',views.FunctionOne.as_view(),name='FunctionOne')
]