from django.urls import path

from . import views
from .views import StudentTable, DepartmentTable, CourseTable, ProfessorTable, EnrollmentTable, GradeTable, DynamicQueryExecutor

urlpatterns=[
    # path('tables/data',views.tables,name='data'),
    path('tables/students/',StudentTable.as_view(),name='students'),
    path('tables/students/<int:id>',StudentTable.as_view(),name='student-ops'),
    path('tables/departments/',DepartmentTable.as_view(),name='departments'),
    path('tables/departments/<int:id>',DepartmentTable.as_view(),name='department-ops'),
    path('tables/courses/',CourseTable.as_view(),name='courses'),
    path('tables/courses/<int:id>',CourseTable.as_view(),name='course-ops'),
    path('tables/professors/',ProfessorTable.as_view(),name='professors'),
    path('tables/professors/<int:id>',ProfessorTable.as_view(),name='professor-ops'),
    path('tables/enrollments/',EnrollmentTable.as_view(),name='enrollments'),
    path('tables/enrollments/<int:id>',EnrollmentTable.as_view(),name='enrollment-ops'),
    path('tables/grades/',GradeTable.as_view(),name='grades'),
    path('tables/grades/<int:id>',GradeTable.as_view(),name='grade-ops'),
    path('tables/dynamic-query/', DynamicQueryExecutor.as_view(), name='dynamic_query'),
]