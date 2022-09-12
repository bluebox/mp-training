from django.contrib import admin
from .models import Profile, posting, likepost,followerscount
# Register your models here.
admin.site.register(Profile)
admin.site.register(posting)
admin.site.register(likepost)
admin.site.register(followerscount)