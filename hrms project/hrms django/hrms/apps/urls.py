from django.urls import path
from apps.views import index,signin,inside,profileupdation,task,att,ins,taskassign,applyleave,signout,approveleave
from django.views.generic import TemplateView
from apps import  views

urlpatterns = [
     path('',index.as_view()),
     # path('signin',signin.as_view()),
     path('back',TemplateView.as_view(template_name="apps/success.html")),
     path('start',TemplateView.as_view(template_name="apps/signin.html")),
     path('star',inside.as_view()),
     path('features',profileupdation.as_view()),
     path('TASKS',task.as_view()),
     path('att',att.as_view()),
     path('ins',ins.as_view()),
     path('tasksassign',taskassign.as_view()),
     path('appyleave',applyleave.as_view()),
     path('lv',TemplateView.as_view(template_name="apps/leave.html")),
     path('signout',signout.as_view(),name="signout"),
     path('log',views.logApi),
     path('signin',signin.as_view()),
     path('approve',approveleave.as_view())
     # path('set',views.setcookie)


]
