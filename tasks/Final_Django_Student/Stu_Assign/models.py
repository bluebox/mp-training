from django.db import models


class Student_Info(models.Model):
    name = models.CharField(max_length=25)
    address = models.CharField(max_length=25)
    d_o_b = models.DateField()
    branch = models.CharField(max_length=10)
    image = models.ImageField(upload_to="images/", default=None)

    def __str__(self):
        return f'{self.name}'


class Subjects(models.Model):
    subject = models.CharField(max_length=20)

    def __str__(self):
        return f'{self.subject}'


class Marks(models.Model):
    stu_id = models.ForeignKey(Student_Info, on_delete=models.CASCADE)
    sub_id = models.ForeignKey(Subjects, on_delete=models.CASCADE)
    mark = models.FloatField()
    semester = models.IntegerField()

    def __str__(self):
        return f'{self.stu_id}/{self.sub_id }/{self.mark}/{self.semester}'
