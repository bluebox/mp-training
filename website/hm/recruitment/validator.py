from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _

def validate_email(value):
    if value[-7:] == '@xyz.com' :
        raise ValidationError(
            _('%(value)s is not an valid email'),
            params={'value': value},
        )