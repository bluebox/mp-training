"""urls of the project"""
from django.urls import path

from project import views, project_manager

urlpatterns = [
    # -----------Teacher
    path('teacher/', views.InstructorView.as_view()),
    path('teacher/<int:pk>/', views.InstructorView.as_view()),
    path('teacher/instructor-login/', project_manager.instructor_login),
    path('teacher/dashboard/<int:pk>/', views.TeacherDashboard.as_view()),
    # Category
    path('sub_category/', views.SubCategoryList.as_view()),
    # Course
    path('course/', views.CourseList.as_view()),
    path('course/<int:pk>/', views.CourseList.as_view()),
    path('allcourse/', views.AllCourseList.as_view()),
    path('allcourse/<int:pk>/', views.AllCourseList1.as_view()),
    path('search-courses/<str:searchData>/', views.SearchCourseList.as_view()),
    # Teacher Course
    path('teacher_courses/<int:id>', views.TeacherCourseList.as_view()),
    path('teacher_courses_delete/<int:pk>', views.CourseList.as_view()),
    # Add Topic Videos url
    path('topic/', views.TopicList.as_view()),
    path('topic/<int:pk>', views.TopicList.as_view()),
    # Specific Course Topics
    path('all_topic_videos/<int:course_id>', views.TeacherCourseChapterList.as_view()),

    # S----------Student #
    path('student/', views.StudentList.as_view()),
    path('student/post/', views.StudentViewPost.as_view()),
    path('student/<int:pk>/', views.StudentDetail.as_view()),
    path('student/dashboard/<int:pk>/', views.StudentDashboard.as_view()),
    path('user/student-login/', project_manager.student_login),
    path('enroll_course/', views.EnrollCoursePost.as_view()),
    path('course-rating-post/', views.CourseRatingPost.as_view()),
    path('course-rating/', views.CourseRatingList.as_view()),
    path('get-reviews/', views.CourseRatingList.as_view()),
    path('fetch_rating_status/<int:user_id>/<int:course_id>', project_manager.fetch_rating_status),
    path('fetch_enroll_status/<int:user_id>/<int:course_id>', project_manager.fetch_enroll_status),
    path('fetch_enrolled_students/', views.EnrolledStudentList.as_view()),
    path('fetch_enrolled_students/<int:course_id>', views.EnrolledStudentList.as_view()),
    path('fetch_enrolled_courses/<int:student_id>', views.EnrolledCourseList.as_view()),
    path('add-favorite-course/', views.StudentFavoriteCourseList.as_view()),
    path('fetch-favorite-course/<int:student_id>', views.StudentFavoriteCourseList.as_view()),
    path('fetch-favorite-status/<int:student_id>/<int:course_id>', project_manager.fetch_favorite_status),
    path('remove-favorite-course/<int:student_id>/<int:course_id>', project_manager.remove_favorite_course),
]
