from django.contrib import admin
from .models import Discussion, Profile, Skill, Problem, Tag, TopicTag, Solved, Comment, ProblemVotes
from django.contrib.auth.models import User

admin.site.register(Profile)
admin.site.register(Skill)
admin.site.register(Problem)
admin.site.register(TopicTag)
admin.site.register(Tag)
admin.site.register(Solved)
admin.site.register(Discussion)
admin.site.register(ProblemVotes)