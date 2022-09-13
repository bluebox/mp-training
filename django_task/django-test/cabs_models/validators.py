from django.core.exceptions import ValidationError
# --------------validators-------------
def password_length(value):
    if len(value)<8:
        raise ValidationError(
            ('%(value)s is not 8 character long'),
            params={'value': value},
        )