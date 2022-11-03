from lib2to3.pgen2.tokenize import generate_tokens
from django.contrib.auth.tokens import PasswordResetTokenGenerator
from six import text_type


class TokenGenerator(PasswordResetTokenGenerator):
    def _make_hash_value(self, user, timestamp: int):
        return (
            text_type(user.pk) + text_type(timestamp) + text_type(user.is_active)  
        )


generate_token = TokenGenerator()