from django.db import models


class Airport(models.Model):
    airport_id = models.PositiveIntegerField()
    airport_name = models.CharField(max_length=50)
    airport_owner = models.CharField(max_length=50)
    city = models.CharField(max_length=50)


class Terminal(models.Model):
    terminal_id = models.PositiveIntegerField()
    terminal_description = models.TextField(max_length=200)
    airport_id = models.ForeignKey(Airport, on_delete=models.CASCADE)



