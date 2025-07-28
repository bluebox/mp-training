from django.db import models

class Book(models.Model):
    title = models.CharField(max_length=255)
    author = models.CharField(max_length=255)
    category = models.CharField(max_length=255)
    status = models.BooleanField(default=True)
    availablity = models.BooleanField(default=True)

    class Meta:
        db_table = "Book"

    def __str__(self):
        return self.title
