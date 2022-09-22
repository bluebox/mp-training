from django.urls import path,include
from . import views
urlpatterns = [
    path('admin_login/', views.homepage,name="homepage"),
    # -------------
    path('login_com/', views.logincompulsory,name="logincompulsory"),
    # -------------
    path('admin_login/logincheck/', views.logincheck,name="logincheck"),
    # -------------
    path('adminpage/', views.adminpage,name="adminpage"),
    # -------------
    path('adminpage/studenttable/', views.studenttable,name="studenttable"),
    path('adminpage/studenttable/<int:id>', views.studenttable,name="studenttableid"),
    path('adminpage/studenttable/<str:de>/<int:id>', views.studenttable,name="studenttabledelid"),
    # -------------
    path('adminpage/coursetable/', views.coursetable,name="coursetable"),
    path('adminpage/coursetable/<int:id>', views.coursetable,name="coursetableid"),
    path('adminpage/coursetable/<str:de>/<int:id>', views.coursetable,name="coursetabledelid"),
    # -------------
    path('adminpage/subjecttable/', views.subjecttable,name="subjecttable"),
    path('adminpage/subjecttable/<int:id>', views.subjecttable,name="subjecttableid"),
    path('adminpage/subjecttable/<str:de>/<int:id>', views.subjecttable,name="subjecttabledelid"),
    # -------------
    path('adminpage/facultytable/', views.facultytable,name="facultytable"),
    path('adminpage/facultytable/<int:id>', views.facultytable,name="facultytableid"),
    path('adminpage/facultytable/<str:de>/<int:id>', views.facultytable,name="facultytabledelid"),
    # --------------
    path('adminpage/assignsubjects/', views.assignsubjects,name="assignsubjects"),
    path('adminpage/assignsubjects/<int:id>', views.assignsubjects,name="assignsubjectsid"),
    path('adminpage/assignsubjects/<str:de>/<int:id>', views.assignsubjects,name="assignsubjectsdelid"),
    # --------------
    path('adminpage/entermarks/', views.entermarks,name="entermarks"),
    # --------------
    path('adminpage/markssubmited/<int:sem>/<int:course>/', views.markssubmited,name="markssubmited"),
    # --------------
    path('adminpage/viewmarksheet/', views.viewmarksheet,name="viewmarksheet"),
    # --------------
    path('adminpage/aboutadmin/', views.aboutadmin,name="aboutadmin"),
    path('adminpage/logout/', views.logout,name="logout")
    
]
