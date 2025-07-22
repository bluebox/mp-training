from django.db import models


class CustomCustomerManager(models.Manager):
    def get_queryset(self):
        return super().get_queryset().filter(state='telangana')