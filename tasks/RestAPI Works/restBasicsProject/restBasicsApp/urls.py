from argparse import Namespace
from doctest import Example
from xml.etree.ElementInclude import include
from django.urls import path, include
from rest_framework.routers import DefaultRouter    
from . views import SendPasswordResetEmailView, UserChangePasswordView, UserLoginView, UserPasswordResetView, UserProfileView, employeDestroy, home, home1,post_home, update_home, delete_home, employeList,employeCreate, employeRetrieve, employeUpdate,employeListAndCreate,employeUpdateRetriveDestroy, employeList1,employeCreate1
from .views import employeRetrieve1,employeUpdate1,employeDestroy1, employeListAndCreate1,employeRetrieveAndUpdate1,employeRetrieveAndDestroy1,employeRetrieveUpdateDestroy1,employeeViewset,employeeViewModelset,employeeViewModelset1
from . views import example,example1, example2,JWTClass
from rest_framework.authtoken.views import obtain_auth_token
from .auth import CustomToken
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView, TokenVerifyView
router = DefaultRouter()
router.register('viewset', employeeViewset, basename='employee_view_set')


router1 = DefaultRouter()
router1.register('viewmodelset', employeeViewModelset, basename='employee_view_model_set')


router2 = DefaultRouter()
router2.register('api', employeeViewModelset1, basename='employee')

urlpatterns = [
    path("1",home, name="home"),
    path("1/<str:id>",home1, name="home"),
    path("post",post_home, name="home"),
    path("update/<int:id>/",update_home, name="home"),
    path("delete/<int:id>/",delete_home, name="home"),
    path("listmixin",employeList.as_view(), name="list"),
    path("createmixin",employeCreate.as_view(), name="create"),
    path("retrievemixin/<int:pk>",employeRetrieve.as_view(), name="retive"),
    path("updatemixin/<int:pk>",employeUpdate.as_view(), name="update"),
    path("destroymixin/<int:pk>",employeDestroy.as_view(), name="destroy"),
    path("listandcreate",employeListAndCreate.as_view(), name="listandcreate"),
    path("update_retrive_destroy/<int:pk>",employeUpdateRetriveDestroy.as_view(), name="update_retrive_destroy"),
    path('concretelist', employeList1.as_view(), name='concrete_list'),
    path('concretecreate', employeCreate1.as_view(), name='concrete_Create'),
    path('concreteretrieve/<int:pk>', employeRetrieve1.as_view(), name='concrete_Retrieve'),
    path('concreteupdate/<int:pk>', employeUpdate1.as_view(), name='concrete_Update'),
    path('concretedelete/<int:pk>', employeDestroy1.as_view(), name='concrete_Destroy'),
    path('concretelistcreate', employeListAndCreate1.as_view(), name='concretelistcreate'),
    path('concreteretrieveupdate/<int:pk>', employeRetrieveAndUpdate1.as_view(), name='concreteretrieveupdate'),
    path('concreteretrievedelete/<int:pk>', employeRetrieveAndDestroy1.as_view(), name='concreteretrievedelete'),
    path('concreteretrieveupdatedelete/<int:pk>', employeRetrieveUpdateDestroy1.as_view(), name='concreteretrieveupdatedelete'),
    path('view/', include(router.urls)),
    path('modelview/', include(router1.urls)),
    path('', include(router2.urls)),
    path('auth/', include('rest_framework.urls',namespace='session_auth')),
    path("example/<int:pk>",example, name="example"),
    path("example1/",example1, name="example1"),
    path("example2/",example2, name="example2"),


    # clent ask for token bilt in class
    path('getauthtoken/', obtain_auth_token),


    #custom side creation of  token generations 
    path('getcustomtoken/', CustomToken.as_view()),

    #  jwt token useage it creates virtual token for current usage of user
    path('gettoken/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('refreshtoken/', TokenRefreshView.as_view(), name='token_refresh'),
    path('verifytoken/', TokenVerifyView.as_view(), name='token_verify'),
    path('basicjwt/', JWTClass.as_view(), name='token_verify'),
    path('login/', UserLoginView.as_view(), name='UserLoginView'),
    path('profile/', UserProfileView.as_view(), name='UserProfileView'),
    path('changepassword/', UserChangePasswordView.as_view(), name='UserChangePasswordView'),
    path('emailPasswordreset/', SendPasswordResetEmailView.as_view(), name='SendPasswordResetEmailView'),
    path('reset_password/<uid>/<token>', UserPasswordResetView.as_view(), name='UserPasswordResetView'),
  


]
