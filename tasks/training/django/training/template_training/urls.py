from django.urls import path
from . import views
urlpatterns = [
    path("",views.homePage,name='home'),
    path("team-page/",views.passContext,name='team-page'),

]