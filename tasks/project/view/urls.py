from django.urls import path
from . import views

urlpatterns = {
    path('', views.firstview, name="view"),
    path('sec', views.secview.as_view(), name="secview"),
    path("json", views.json9.as_view(), name='json'),
    path("json1", views.Json1.as_view(), name='json1'),
    path("geet", views.geet.as_view(), name='geet'),
    path("poostt", views.poostt.as_view(), name='poostt'),

}
