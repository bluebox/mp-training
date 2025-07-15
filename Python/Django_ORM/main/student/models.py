from django.db import models

class Student(models.Model):
    name = models.CharField(max_length=255)
    age = models.IntegerField()
    student_class = models.CharField(max_length=10)
    date_of_admission = models.DateField(auto_now=True)

    class Meta:
        db_table = "student"

    def __str__(self):
        return self.name
