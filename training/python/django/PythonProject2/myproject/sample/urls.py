from django.urls import path
from .views import index, sample, dynamic_url

urlpatterns = [
    path("index/", index, name ="index"),
    # path("sample/", sample, name = "sample"),
    path("sample/<int:id>/", dynamic_url, name="dynamic"),

]
