from django.db import models


class Directors(models.Model):
    director_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=100, blank=True, null=True)
    email = models.CharField(unique=True, max_length=100, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'Directors'


class Moviedirectors(models.Model):
    pk = models.CompositePrimaryKey('director_id', 'movie_id')
    director = models.ForeignKey(Directors, models.DO_NOTHING)
    movie = models.ForeignKey('Movies', models.DO_NOTHING)

    class Meta:
        managed = True
        db_table = 'MovieDirectors'


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


class Users(models.Model):
    user_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=100, blank=True, null=True)
    email = models.CharField(unique=True, max_length=100, blank=True, null=True)
    date_of_birth = models.DateField(blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'Users'


class Watchhistory(models.Model):
    history_id = models.AutoField(primary_key=True)
    user = models.ForeignKey(Users, models.DO_NOTHING, blank=True, null=True)
    movie = models.ForeignKey(Movies, models.DO_NOTHING, blank=True, null=True)
    watch_date = models.DateField(auto_now_add=True)

    class Meta:
        managed = True
        db_table = 'WatchHistory'
