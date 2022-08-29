from django.contrib import admin
from django.urls import path, include
from . import views


urlpatterns=[

    path('first_view', views.FirstView.as_view(), name='first_view'),
    path('params',views.WithParams.as_view(),name='with_params'),
    path('data',views.WithFormData.as_view(),name='data'),
    path('json',views.WithJsonResponse.as_view(),name='json'),

]