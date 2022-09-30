from django.contrib import admin
from .models import subject_details, class_details, batch_details
from .models import faculty_details
from .models import student_details, feedback_by_faculty, feedback_by_student

# Register your models here.

admin.site.register(subject_details)
admin.site.register(class_details)
admin.site.register(batch_details)
# admin.site.register(faculty_details)

@admin.register(faculty_details)
class faculty(admin.ModelAdmin):
    list_display = ['first_name', 'last_name', 'facul_username', 'password']

admin.site.register(student_details)
admin.site.register(feedback_by_faculty)
admin.site.register(feedback_by_student)
