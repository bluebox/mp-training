from django.contrib import admin

from .models import Student, Master, Evaluation, ExamAttemptDetail, Set, SetQuestionNumber, Option, Subject, Question

admin.site.register(Student)
admin.site.register(Master)
admin.site.register(Evaluation)
admin.site.register(ExamAttemptDetail)
admin.site.register(Set)
admin.site.register(SetQuestionNumber)
admin.site.register(Option)
admin.site.register(Subject)
admin.site.register(Question)