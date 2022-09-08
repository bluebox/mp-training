from django.contrib import admin
from .models import professor,department,subjects,students_schedule,students,students_attendence,professor_attendance,department_subjects,student_fee_details,admin_details
# Register your models here.
admin.site.register(professor)
admin.site.register(students)
admin.site.register(subjects)
admin.site.register(students_attendence)
admin.site.register(professor_attendance)
admin.site.register(department_subjects)
admin.site.register(student_fee_details)
admin.site.register(department)
admin.site.register(students_schedule)
admin.site.register(admin_details)
