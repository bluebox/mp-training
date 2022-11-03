from django.contrib import admin
from .models import Profile, posting, likepost,followerscount,Groups,Message
# Register your models here.
admin.site.register(Profile)
admin.site.register(posting)
admin.site.register(likepost)
admin.site.register(followerscount)
@admin.register(Groups)
class GroupAdmin(admin.ModelAdmin):
    list = ['id','name']
@admin.register(Message)
class MessageAdmin(admin.ModelAdmin):
    list = ['id','message','time','group']








