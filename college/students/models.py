from django.db import models

class Branch(models.Model):
    branch = models.CharField(max_length=100)

    class Meta:
        ordering = ['branch']
    
    def __str__(self):
        return self.branch


class Semester(models.Model):
    semester = models.IntegerField(default=0)

    def __str__(self):
        return str(self.semester)


class Subjects(models.Model):
    branch = models.ForeignKey(Branch, on_delete=models.CASCADE)
    semester = models.ForeignKey(Semester, on_delete=models.CASCADE)
    subject = models.CharField(max_length=100)

    def __str__(self):
        return self.subject


class Student(models.Model):
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    date_of_birth = models.DateField()
    branch = models.ForeignKey(Branch, on_delete=models.CASCADE)

    def __str__(self):
        return self.first_name


class Marks(models.Model):
    student = models.ForeignKey(Student, on_delete=models.CASCADE)
    subject = models.ForeignKey(Subjects, on_delete=models.CASCADE)
    semester = models.ForeignKey(Semester, on_delete=models.CASCADE)
    marks = models.IntegerField(default=0)

    def __str__(self):
        return str(self.marks)





