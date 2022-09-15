from django.contrib import admin
from .models import subject_details, class_details, batch_details
from Faculty.models import faculty_details
from Student.models import student_details, feedback_by_faculty, feedback_by_student
# Register your models here.

admin.site.register(subject_details)
admin.site.register(class_details)
admin.site.register(batch_details)
admin.site.register(faculty_details)
admin.site.register(student_details)
admin.site.register(feedback_by_faculty)
admin.site.register(feedback_by_student)
