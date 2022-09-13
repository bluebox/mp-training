from django.db import models
from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _
# Create your models here.

def validate_name(value):
    if value[0]=="@":
        raise ValidationError(
            _('%(value)s should start with letters'),
            params={'value': value},)

class Batsman(models.Model):
    bat_id = models.CharField(primary_key=True, max_length=20)
    bat_name = models.CharField(max_length=30, null=False, blank=False)
    matches_played =models.ManyToManyField('Matches')

    class Meta:
        managed= False


class Bowler(models.Model):
    bow_id = models.CharField(primary_key=True, max_length=20)
    bow_name = models.CharField(max_length=30, null=False, blank=False,validators=[validate_name])
    matches_played = models.ManyToManyField('Matches')

    class Meta:
        managed = True


class Matches(models.Model):
    match_id = models.CharField(primary_key=True, max_length=20)
    match_hosted = models.CharField(max_length=30, null=False, blank=False)


