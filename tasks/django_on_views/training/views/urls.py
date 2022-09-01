from django.urls import path
from views.views import MyView,conver
from django.views.generic import TemplateView
urlpatterns = [
    # path('',views.func_based,name="function_based_view"),
    path('about/', TemplateView.as_view(template_name="views/index.html")),
    path('class',MyView.as_view()),
    path('conver',conver.as_view())
]
