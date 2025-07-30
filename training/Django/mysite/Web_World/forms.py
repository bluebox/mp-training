from django import forms
# from mysite.Web_World.models import Model_example

class Myform(forms.Form):
    name=forms.CharField(label='your name',max_length=50)
    age=forms.IntegerField(label='your age')
    email=forms.EmailField(label='email id')

# class Example(forms.ModelForm):
#     class Meta:
#         model  = Model_example
#         fields = ['name', 'phone', 'age', 'email']


