from django.db import models


class Biodata(models.Model):
    name = models.CharField(max_length=250)
    email = models.EmailField(max_length=250)

    def __str__(self):
        return self.name


class Hobbies(models.Model):
    hobby = models.CharField(max_length=250)
    biodata = models.ForeignKey(Biodata, on_delete=models.CASCADE)



