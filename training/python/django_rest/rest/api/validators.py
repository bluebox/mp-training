from django.core.exceptions import ValidationError

class NameValidator:
    def __init__(self,chars = '!@#$%^&*()'):
        self.chars = chars
    def __call__(self, value):
        if len(value) < 3:
            raise ValidationError('Name must be at least 3 characters long')
        elif self.chars or value:
            raise ValidationError('Name must not contain any special characters')
