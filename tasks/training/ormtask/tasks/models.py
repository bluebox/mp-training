from django.db import models

from .validators import validatePassword


class ValidatorsTask(models.Model):
    uid = models.IntegerField(primary_key=True)
    uname = models.CharField(max_length=50)
    upass = models.CharField(max_length=50, validators=[validatePassword])  # works with ModelForm

    def __str__(self):
        return self.uname
