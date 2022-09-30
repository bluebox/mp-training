from django.contrib import admin
from .models import *
from django import forms
from django.core.exceptions import ValidationError
from django.db.models import Q

# Register your models here.
admin.site.register(Messages1)

class Messages1(admin.TabularInline):
    model = Messages1


# class ThreadForm(forms.ModelForm):
#     def clean(self):
#         """
#         This is the function that can be used to
#         validate your model data from admin
#         """
#         super(ThreadForm, self).clean()
#         first_person = self.cleaned_data.get('first_person')
#         second_person = self.cleaned_data.get('second_person')

#         lookup1 = Q(first_person=first_person) & Q(second_person=second_person)
#         lookup2 = Q(first_person=second_person) & Q(second_person=first_person)
#         lookup = Q(lookup1 | lookup2)
#         qs = ThreadingTable.objects.filter(lookup)
#         if qs.exists():
#             raise ValidationError(f'Thread between {first_person} and {second_person} already exists.')


class ThreadAdmin(admin.ModelAdmin):
    inlines = [Messages1]
    # form = ThreadForm     
    class Meta:
        model = ThreadingTable


admin.site.register(ThreadingTable, ThreadAdmin)