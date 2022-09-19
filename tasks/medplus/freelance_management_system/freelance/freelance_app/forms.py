from django.forms import ModelForm
from .models import freelancer_details
class FreelanceForm(ModelForm):
    class Meta(ModelForm):
        model = freelancer_details
        fields = ['country']

