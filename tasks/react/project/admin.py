from django.contrib import admin
from .models import *

admin.site.register(Instructor)
admin.site.register(Student)
admin.site.register(Course)
admin.site.register(Category)
admin.site.register(SubCategory)
admin.site.register(Topic)
admin.site.register(StudentCourseEnrollment)
admin.site.register(StudentFavoriteCourse)
admin.site.register(CourseRating)


