from django.contrib.auth.base_user import BaseUserManager
# from pkg_resources import _


class MyUserManager(BaseUserManager):
    def create_user(self, email, password, password2=None, first_name=None, last_name=None,**extra_fields):
        """
        Creates and saves a User with the given email, date of
        birth and password.
        """
        if not email:
            raise ValueError('Users must have an email address')
        email = self.normalize_email(email)
        extra_fields.setdefault('is_active', True)

        user = self.model(
            email=email,
            **extra_fields
        )

        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, email, password=None, **extra_fields):
        """
        Creates and saves a superuser with the given email, date of
        birth and password.
        """
        extra_fields.setdefault('is_staff', True)
        extra_fields.setdefault('is_superuser', True)
        extra_fields.setdefault('is_active', True)
        extra_fields.setdefault('is_admin', True)

        if extra_fields.get('is_staff') is not True:
            raise ValueError(('super user must have is_staff=True'))
        if extra_fields.get('is_superuser') is not True:
            raise ValueError(('super user must have is_superuser=True'))

        return self.create_user(email, password, **extra_fields)


#
# class MyUserManager(BaseUserManager):
#     def create_user(self, email, password=None, **extra_fields):
#         """
#         Creates and saves a User with the given email, date of
#         birth and password.
#         """
#         if not email:
#             raise ValueError('Users must have an email address')
#
#         user = self.model(
#             email=self.normalize_email(email),
#            **extra_fields
#         )
#
#         user.set_password(password)
#         user.save(using=self._db)
#         return user
#
#     def create_superuser(self, email, password=None,**extra_fields):
#         """
#         Creates and saves a superuser with the given email, date of
#         birth and password.
#         """
#         user = self.create_user(
#             email,
#             password=password,
#             **extra_fields
#         )
#         user.is_admin = True
#         user.is_superuser = True
#         user.is_staff = True
#         user.save(using=self._db)
#         return user