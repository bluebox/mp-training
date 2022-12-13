from django.contrib import admin

from post.models import Post, Comments, Uploader, User

# Register your models here.

@admin.register(Post)
class TweetAdmin(admin.ModelAdmin):
    list_display = ['name', 'mobile', 'email', 'images', 'likes','score', 'shares']
    list_filter = ['score', 'likes','shares']
admin.site.register(Comments)
admin.site.register(Uploader)
admin.site.register(User)

