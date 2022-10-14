from django.urls import path
from . import views

urlpatterns = [
    path('restfr/', views.func_based_views, name='details'),
    path('restframe/<str:name>/', views.func_based_views_with_pk, name='func_based_views_with_pk'),

    path('api_view/', views.func_based_api_views, name='api_view'),
    path('api_view/<str:name>/', views.func_based_api_views_with_pk, name='func_based_api_views_with_pk'),


    path('class_apiview/', views.ClassBasedViews.as_view(), name='ClassBasedViews'),
    path('class_apiview/<str:name>/', views.ClassBasedViewsWithPk.as_view(), name='ClassBasedViewsWithPk'),


    path('generic_view/<str:first_name>/', views.GenericAPIView.as_view(), name='GenericAPIView'),
]