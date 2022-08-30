
from django.urls import path,re_path
from . import views

urlpatterns = [
    path('<str:para>', views.one_para,name="one_para"),
    path('two_para/<int:para1>/<int:para2>', views.two_para,name="two_para"),
    path('nested', views.nested,name="nested"),
    # re_path(r'^regex/[0-5]{2}[a-z]{3}$', views.regex_para,name="regex"),
    path('uuid/<uuid:para>', views.uuid_para,name="uuid"),
    path('slug_format/<slug:para',views.slug_para,name='slug_para')


]