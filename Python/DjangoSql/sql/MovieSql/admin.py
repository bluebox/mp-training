from django.contrib import admin

from .models import Users, MovieDirectors, Watchhistory, Movies, Directors

admin.site.register(Users)
admin.site.register(MovieDirectors)
admin.site.register(Watchhistory)
admin.site.register(Movies)
admin.site.register(Directors)