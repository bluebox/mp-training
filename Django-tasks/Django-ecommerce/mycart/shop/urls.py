from django.contrib import admin
from django.urls import path
from . import views
urlpatterns = [
    path("", views.index, name="ShopHome"),
    path("about/", views.about, name="AboutUs"),
    path("contact/", views.contact, name="ContactUs"),
    path("tracker", views.tacker, name="TrackingStatus"),
    path("search/", views.search, name="Search"),
    path("products/<int:myid>", views.prodView, name="productView"),
    path("checkout/", views.checkout, name="Checkout"),
    path("login/", views.loginPage, name="login"),
    path("logout/", views.logoutUser, name="logout"),
    path("signup/", views.signup, name="signup")

]
