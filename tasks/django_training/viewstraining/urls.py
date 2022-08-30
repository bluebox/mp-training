from django.urls import path, include
from . import views

urlpatterns = [
    path('', views.function_view, name='f_views'),
    path('1', views.function_view_with_decorators, name='f_views_decorator'),
    path('2/', views.AboutView.as_view(), name='c_generic_views'),
    path('3/', views.CViewWithParams.as_view(), name='c_views_with_params'),
    path('4/', views.CViewWithFormData.as_view(), name='c_views_with_form_data'),
    path('5/', views.CViewWithJsonResponse.as_view(), name='c_views_with_form_data'),

    ]
