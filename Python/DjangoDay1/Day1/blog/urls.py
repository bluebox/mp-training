from django.urls import path
from .views import home, home_template, blog_detail, login_view, dashboard

urlpatterns=[
    path('home/',home,name='home'),
    path('home_template/',home_template,name='home_template'),
    path('<int:id>/', blog_detail, name='single'),
    path('login/',login_view,name='login'),
    path('dashboard/',dashboard,name='dashboard')
]