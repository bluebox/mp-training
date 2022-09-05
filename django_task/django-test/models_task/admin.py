from django.contrib import admin
from .models import admin_login,courses,subject,students,students_documents,student_login,faculties,faculties_documents,faculties_login,assign_subject,enter_marks

admin.site.register(admin_login)
admin.site.register(courses)
admin.site.register(subject)
admin.site.register(students)
admin.site.register(students_documents)
admin.site.register(student_login)
admin.site.register(faculties)
admin.site.register(faculties_documents)
admin.site.register(faculties_login)
admin.site.register(assign_subject)
admin.site.register(enter_marks)