from django.db import models
from recruitment.models import candidate_info


from django.contrib.auth.models import (
    BaseUserManager, AbstractBaseUser
)


class UserManager(BaseUserManager):
    def create_user(self, email, name,cid, password=None,password2=None):
        """
        Creates and saves a User with the given email, date of
        birth and password.
        """
        if not email:
            raise ValueError('Users must have an email address')

        user = self.model(
            email=self.normalize_email(email),
            name=name,
            cid=cid
        )

        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, email, name,cid, password=None):
        """
        Creates and saves a superuser with the given email, date of
        birth and password.
        """
        user = self.create_user(
            email,
            password=password,
            cid=cid,
            name=name
        )
        user.is_admin = True
        user.save(using=self._db)
        return user


class User(AbstractBaseUser):
    email = models.EmailField(
        verbose_name='email',
        max_length=255,
        unique=True,
    )
    name=models.CharField(max_length=100)
    cid = models.ForeignKey(candidate_info, on_delete=models.CASCADE)
    is_active = models.BooleanField(default=True)
    is_admin = models.BooleanField(default=False)

    objects = UserManager()

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = ['name','cid']

    def __str__(self):
        return self.email

    def has_perm(self, perm, obj=None):
        "Does the user have a specific permission?"
        # Simplest possible answer: Yes, always
        return self.is_admin

    def has_module_perms(self, app_label):
        "Does the user have permissions to view the app `app_label`?"
        # Simplest possible answer: Yes, always
        return True

    @property
    def is_staff(self):
        "Is the user a member of staff?"
        # Simplest possible answer: All admins are staff
        return self.is_admin

class emp_login(models.Model):
    eid =models.IntegerField(unique=True)
    loginid=models.CharField(max_length=50,default="")
    password = models.CharField(max_length=50,default="")



class apply_candiate_login(models.Model):
    cid = models.ForeignKey(candidate_info, on_delete=models.CASCADE)
    loginid=models.CharField(max_length=50,default="")
    password = models.CharField(max_length=50,default="")