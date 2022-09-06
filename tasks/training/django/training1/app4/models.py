from django.db import models

# Create your models here.
class CricketBoard(models.Model):
    board_id = models.CharField(max_length=50,primary_key=True)
    board_name = models.CharField(max_length=50,null=False, blank=False)
    board_chairman = models.CharField(max_length=50, null=True)

    def __str__(self):
        return self.board_name

class Coach(models.Model):
    coach_id = models.CharField(max_length=40, primary_key=True)
    coach_name = models.CharField(max_length=40, null=False, blank=False)
    coach_country = models.CharField(max_length=40, null=True, blank=True)
    coach_experience = models.IntegerField(null=True, blank=True)

    def __str__(self):
        return self.coach_name


class Captain(models.Model):
    captain_id = models.CharField(max_length=30, primary_key=True)
    no_of_wins = models.IntegerField(null=True, blank=True)
    no_of_loses = models.IntegerField(null=True, blank=True)

    def __str__(self):
        return self.captain_id

class Team(models.Model):
    tean_id = models.CharField(max_length=40, primary_key=True)
    team_name = models.CharField(max_length=50,null=False,blank=False)
    team_rank = models.IntegerField(null=True, blank=True)
    board_id = models.ForeignKey(CricketBoard, on_delete=models.SET_NULL, null=True, blank=True)
    coach_id= models.ForeignKey(Coach, on_delete=models.SET_NULL, null=True, blank=True)
    captain_id = models.ForeignKey(Captain, on_delete=models.SET_NULL, null=True, blank=True)

    def __str__(self):
        return self.team_name

class Batsman(models.Model):
    batsman_id = models.CharField(max_length=20, primary_key=True)
    type_of_batsman = models.CharField(max_length=30, null=False, blank=False)
    test_runs = models.IntegerField(null=True, blank=True)
    odi_runs = models.IntegerField(null=True, blank=True)
    t20_runs = models.IntegerField(null=True, blank=True)

    def __str__(self):
        return self.batsman_id


class Bowler(models.Model):
    bowler_id = models.CharField(max_length=20, primary_key=True)
    type_of_bowler = models.CharField(max_length=30, null=False, blank=False)
    test_wkts = models.IntegerField(null=True, blank=True)
    odi_wkts = models.IntegerField(null=True, blank=True)
    t20_wkts = models.IntegerField(null=True, blank=True)

    def __str__(self):
        return self.bowler_id

class WicketKeeper(models.Model):
    wk_id = models.CharField(max_length=20, primary_key=True)
    no_of_catches = models.IntegerField(null=True, blank=True)
    no_of_stumpings = models.IntegerField(null=True, blank=True)
    no_of_runouts = models.IntegerField(null=True, blank=True)

    def __str__(self):
        return self.wk_id


class Player(models.Model):
    player_id = models.CharField(max_length=20, primary_key=True)
    player_name = models.CharField(max_length=30, null=False, blank=False)
    player_age = models.IntegerField(null=False)
    matches_played = models.IntegerField(null=True, blank=True)
    player_country = models.CharField(max_length=20, null=True, blank=True)
    team_id = models.ForeignKey(Team, on_delete=models.SET_NULL, null=True, blank=True)
    captain_id = models.ForeignKey(Captain, on_delete=models.SET_NULL, null=True, blank=True)
    batsman_id = models.ForeignKey(Batsman, on_delete=models.SET_NULL, null=True, blank=True)
    bowler_id = models.ForeignKey(Bowler, on_delete=models.SET_NULL, null=True, blank=True)
    wk_id = models.ForeignKey(WicketKeeper, on_delete=models.SET_NULL, null=True, blank=True)

    def __str__(self):
        return self.player_name







