from django.db import models
from django.db.models import CASCADE
from django.views.decorators.csrf import csrf_exempt


class Directors(models.Model):
    director_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=100, blank=True, null=True)
    email = models.CharField(unique=True, max_length=100, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'Directors'

    def __str__(self):
        return self.name


class Movies(models.Model):
    movie_id = models.AutoField(primary_key=True)
    title = models.CharField(max_length=100, blank=True, null=True)
    genre = models.CharField(max_length=50, blank=True, null=True)
    duration_minutes = models.IntegerField(blank=True, null=True)
    current_views = models.IntegerField(blank=True, null=True)
    max_views = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'Movies'

    def __str__(self):
        return self.title


class MovieDirectors(models.Model):
    director = models.ForeignKey('Directors', on_delete=models.CASCADE, db_column='director_id')
    movie = models.ForeignKey('Movies', on_delete=models.CASCADE, db_column='movie_id')

    class Meta:
        managed = True
        db_table = 'MovieDirectors'
        unique_together = ('director', 'movie')

    def __str__(self):
        return f"{self.director} - {self.movie}"



class Users(models.Model):
    user_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=100, blank=True, null=True)
    email = models.EmailField(unique=True, max_length=100, blank=True, null=True)
    date_of_birth = models.DateField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'Users'

    def __str__(self):
        return self.name


class Watchhistory(models.Model):
    history_id = models.AutoField(primary_key=True)
    user = models.ForeignKey(Users, on_delete=CASCADE, blank=True, null=True)
    movie = models.ForeignKey(Movies, on_delete=CASCADE, blank=True, null=True)
    watch_date = models.DateField(null=True, auto_now_add=True)

    class Meta:
        managed = True
        db_table = 'WatchHistory'

    def __str__(self):
        return f"{self.movie}-{self.user}"


