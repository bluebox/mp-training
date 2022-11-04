from django.urls import path
from . import views
urlpatterns=[
    path('fun-based/',views.fun_based_view, name='fun-based'),
    path('fun-decorators/',views.fun_based_decorators, name='fun-decorators'),
    path('class-based/',views.GreetingView.as_view(), name='greet-class'),
    path('list-view',views.ListTeam.as_view(),name='list-team'),
    path('detail-view/<pk>/',views.DetailMember.as_view(),name='detail'),
]