from unicodedata import name
from django.urls import path,include
from rest_framework import routers
from .views import AppointmentViewSet, DoctorViewSet, GetUser, Login
from .views import Logout, PatientViewSet, StaffViewSet,AllUsersViewSet
from .views import UserView,DatewiseSlotViewSet,SlotFilter,GetDoctor
from .views import GetPatient
router = routers.SimpleRouter()
router.register(r'patient', PatientViewSet)
router.register(r'doctor', DoctorViewSet)
router.register(r'staff', StaffViewSet)
router.register(r'allUser', AllUsersViewSet)
router.register(r'appointment', AppointmentViewSet)
router.register(r'DatewiseSlot', DatewiseSlotViewSet)

urlpatterns = [
	path('register/',UserView.as_view(),name='userView'),
	path('login/',Login.as_view(),name='LoginView'),
	path('getuser/',GetUser.as_view(),name='getuserView'),
	path('logout/',Logout.as_view(),name='LogoutView'),
	path('slotfilter/<str:doctor>/<str:date>/',SlotFilter.as_view(),name='SlotfilterView'),
	path('getdoctor/<str:email>/',GetDoctor.as_view(),name='docfilter'),
	path('getpatient/<str:email>/',GetPatient.as_view(),name='patfiler'),
	# path('signup/patient/',PatientRegistrationView.as_view(),name='signup_paitent'),
	# path('signup/doctor/',DoctorRegistrationView.as_view(),name='signup_doctor'),
	# path('signin/',UserLoginView.as_view()),
]
urlpatterns += router.urls