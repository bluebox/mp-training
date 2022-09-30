from django.contrib import admin

# Register your models here.
from .models import User
from django.contrib.auth.admin import UserAdmin as BaseUserAdmin

class UserAdmin(BaseUserAdmin):
    # The forms to add and change user instances
    # form = UserChangeForm
    # add_form = UserCreationForm

    # The fields to be used in displaying the User model.
    # These override the definitions on the base UserAdmin
    # that reference specific fields on auth.User.
    list_display = ('id','email', 'username','first_name','last_name', 'is_admin','is_active','is_staff')
    list_filter = ('is_admin',)
    fieldsets = (
        ('user Credentials', {'fields': ('email', 'password')}),
        ('Personal info', {'fields': ('username','first_name','last_name',)}),
        ('Permissions', {'fields': ('is_admin','is_superuser', 'is_active')}),
    )
    # add_fieldsets is not a standard ModelAdmin attribute. UserAdmin
    # overrides get_fieldsets to use this attribute when creating a user.
    add_fieldsets = (
        (None, {
            'classes': ('wide',),
            'fields': ('email', 'username','first_name','last_name', 'password1', 'password2'),
        }),
    )
    search_fields = ('email','username')
    ordering = ('id','email')
    filter_horizontal = ()


# Now register the new UserAdmin...
admin.site.register(User, UserAdmin)
# ... and, since we're not using Django's built-in permissions,
# unregister the Group model from admin.
