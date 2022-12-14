from django.urls import path
from . import views

app_name = 'students'
urlpatterns = [
    path('', views.studentsHomePage, name='home'),
    path('studentsList/<int:id>', views.studentDetails, name='studentDetails'),
    path('studentsList/filterByBranch', views.filterByBranch, name='filterByBranch'),
    path('studentsList/searchStudent', views.searchByID, name='searchByID'),
    path('studentsList/<int:id>/marks', views.studentMarks, name='marks'),
    path('addStudent/newStudent', views.addStudent, name='addingDetails'),
    path('addStudent', views.renderForm, name='form')

]
