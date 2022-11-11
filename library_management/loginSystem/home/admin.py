from django.contrib import admin
from home.models import book,ExtendedUser
from django.contrib.auth.admin import UserAdmin
# Register your models here.

# admin.site.register(book)
# admin.site.register(BnR)
admin.site.register(ExtendedUser)

@admin.register(book)
class bookadmin(admin.ModelAdmin):
    list_display=('BookName','AuthorName','price','genre','status','Username','BorrowDate','DueDate','ReturnDate')

