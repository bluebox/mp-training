from django.urls import path
from OrderFood.views import Index



urlpatterns = [
    path('', Index.as_view()),

]