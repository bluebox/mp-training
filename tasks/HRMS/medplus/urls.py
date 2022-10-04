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
    path('leave',views.leave)

]
