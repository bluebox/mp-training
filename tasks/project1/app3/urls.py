from django.urls import path
from .import views

urlpatterns = [

    path('view/', views.ClassBasedView.as_view(), name="view"),
    path('view/generic/', views.Generic.as_view(), name="generic"),
    path('view/generic2/', views.Generic2.as_view(), name="generic2"),
    path('<int:pk>/', views.Generic3.as_view(), name="generic3")

]