from django.urls import  path

from .views import home, CustomClassMiddleware, template_example, cookies_example, get_cookie_example, \
    delete_cookie_example, update_cookie_example

urlpatterns=[
    path('home/',home,name='home'),
    path('CustomClassMiddleware/',CustomClassMiddleware,name='CustomClassMiddleware'),
    path('template_example/',template_example,name='template_example'),
    path('cookies_example/',cookies_example,name='cookies_example'),
    path('get_cookie_example/',get_cookie_example,name='get_cookie_example'),
    path('delete_cookie_example/',delete_cookie_example,name='delete_cookie_example'),
    path('update_cookie_example/',update_cookie_example,name='update_cookie_example'),
]