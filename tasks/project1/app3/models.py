from django.db import models
from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _

def validate_odd(value):
    if value % 2 == 0:
        raise ValidationError(
            _('%(value)s is not an even number'),
            params={'value': value},
        )

class Biodata1(models.Model):
    name = models.CharField(max_length=250)
    email = models.EmailField(max_length=250)

    def __str__(self):
        return self.name

    class Meta:
        managed = True ;


class Hobbies1(models.Model):
    hobby = models.CharField(max_length=250)
    biodata = models.ForeignKey(Biodata1, on_delete=models.CASCADE)

    class Meta:
        managed = False;


class MyModel(models.Model):
    odd_field = models.IntegerField()

