from django.urls import path
from . import views

urlpatterns = [
    path('', views.index),
    path('signin',views.signin),
    path('features',views.features),
    path('TASKS',views.TASKS),
    path('att',views.atts),
    path('ins',views.ins),
    path('tasksassign',views.ta),
    path('lv',views.lv),
    path('leaves',views.leave),
    path('signup',views.signup),
    path('acs',views.acs),
    path('start',views.start),
    path('signout',views.so)

]
