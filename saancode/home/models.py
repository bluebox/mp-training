from cgi import print_form
from codecs import getencoder
from email.policy import default
from random import choices
from re import T
from tokenize import blank_re
from django.db import models
from django.contrib.auth.models import User
from django.db.models.signals import post_save
from django.dispatch import receiver
import uuid

class Profile(models.Model):
    user = models.OneToOneField(User, primary_key=True, 
          on_delete = models.CASCADE)
    full_name = models.CharField(max_length=30)
    GENDER_CHOICES = (
        ('M', 'Male'),
        ('F', 'Female'),
    )
    gender = models.CharField(max_length=1, choices=GENDER_CHOICES, blank=True)
    location = models.CharField(max_length=50, blank=True)
    bio = models.CharField(max_length=100, blank=True)
    work = models.CharField(max_length=50, blank=True)
    education = models.CharField(max_length=50, blank=True)
    mobile = models.CharField(max_length=30, default='', blank=True)
    github = models.CharField(max_length=50, default='', blank=True)
    facebook = models.CharField(max_length=50, default='', blank=True)
    instagram = models.CharField(max_length=50, default='', blank=True)
    streak = models.IntegerField(default=0)
    # password = models.CharField(max_length=30)

    def __str__(self):
        return self.user.username

@receiver(post_save, sender=User)
def create_user_profile(sender, instance, created, **kwargs):
    print(kwargs)
    if created:
        print(instance.password)
        Profile.objects.create(user=instance)


class Skill(models.Model):
    skill_id = models.UUIDField(default=uuid.uuid4, primary_key=True, editable=False)
    profile_id = models.ForeignKey(Profile, on_delete=models.CASCADE)
    skill_name = models.CharField(max_length=30, blank=False, null=False)

    def __str__(self):
        return self.profile_id.user.username

class Problem(models.Model):
    problem_id = models.AutoField(primary_key=True, editable=False)
    creator_id = models.ForeignKey(User, on_delete=models.SET_NULL, null=True)
    problem_name = models.CharField(max_length=30, blank=False, null=False, unique=True)
    description = models.TextField(max_length=1000, unique=True, blank=False, null=False)
    hints = models.TextField(max_length=50, blank=False, null=False)
    test_cases = models.TextField(max_length=500, blank=False, null=False)
    outputs = models.TextField(max_length=500, blank=False, null=False)
    json_test_cases = models.JSONField(blank=False, null=False)
    # likes = models.IntegerField(default=0)
    likes = models.PositiveIntegerField(default=0)
    dislikes = models.PositiveIntegerField(default=0)
    accuracy = models.FloatField(default=0)
    # difficulty_level_options = (
    #     ('E', 'easy'), 
    #     ('M', 'medium'),
    #     ('H', 'hard'),
    # )
    difficulty_level_options = (
        (0, 'easy'), 
        (1, 'medium'),
        (2, 'hard'),
    )
    # difficulty_level = models.CharField(max_length=1, choices=difficulty_level_options, blank=False, default='E', null=False)
    difficulty_level = models.IntegerField(choices=difficulty_level_options, blank=False, default=0, null=False)

    # def deleteLikes(self):
    #     self.likes -= 1
    #     self.save()

    # def addLikes(self):
    #     self.likes += 1
    #     self.save()

    # def deleteDisLikes(self):
    #     self.dislikes -= 1
    #     self.save()

    # def addDisLikes(self):
    #     self.dislikes += 1
    #     self.save()

    def __str__(self):
        return self.problem_name


class ProblemVotes(models.Model):
    votes_id = models.AutoField(primary_key=True, editable=False)
    problem_id = models.ForeignKey(Problem, null=False, blank=False, on_delete=models.CASCADE)
    voter_id = models.ForeignKey(User, null=True, blank=True, on_delete=models.SET_NULL)
    vote_options = (
        ('L', 'like'),
        ('D', 'dislike'),
    )
    vote = models.CharField(max_length=1, choices=vote_options)

class Tag(models.Model):
    tag_id = models.AutoField(primary_key=True)
    tag_name = models.CharField(max_length=30, blank=False, null=False)

    def __str__(self):
        return self.tag_name

class TopicTag(models.Model):
    id = models.AutoField(primary_key=True, blank=False, null=False)
    problem_id = models.ForeignKey(Problem, blank=False, null=False,on_delete=models.CASCADE)
    tag_id = models.ForeignKey(Tag, blank=False, null=False, on_delete=models.CASCADE)

    def __str__(self):
        return self.problem_id.__str__()

class Solved(models.Model):
    id = models.AutoField(primary_key=True)
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)
    problem_id = models.ForeignKey(Problem, on_delete=models.CASCADE)
    solution = models.TextField(max_length=500, null=False, blank=False)
    status_choices = (
        (1, 'Accepted'),
        (2, 'Wrong Answer')
    )
    solved_date_time = models.DateTimeField(auto_now_add=True)
    status = models.IntegerField(choices=status_choices, null=False, blank=False)
    result = models.TextField(max_length = 100, null = False, blank = False)

    def __str__(self):
        return f'{self.user_id.username}_P_{self.problem_id.__str__()}'

class Discussion(models.Model):
    discussion_id = models.AutoField(primary_key=True)
    username = models.ForeignKey(User, on_delete=models.CASCADE, null=False, to_field="username", db_column="username")
    problem_id = models.ForeignKey(Problem, on_delete=models.CASCADE, null=False)
    title = models.CharField(max_length=30, null=False, blank=False)
    discussion = models.TextField(max_length=500, null=True, blank=True)
    created_date_time = models.DateTimeField(auto_now_add=True, null=False, blank=False)
    upvotes = models.PositiveIntegerField(default=0, null=False, blank=False)
    downvotes = models.PositiveIntegerField(default=0, null=False, blank=False)

    def __str__(self):
        return self.title

class Blog(models.Model):
    blog_id = models.AutoField(primary_key = True)
    user_id = models.ForeignKey(User, on_delete=models.CASCADE, to_field="username", db_column="username")
    created_date_time = models.DateTimeField(auto_now_add = True)
    title = models.CharField(max_length = 50, null=False, blank=False)
    discussion = models.TextField(max_length=2000, null=False, blank=False)
    tag_choices = (
        ('web development', 'web development'),
        ('game development', 'game development'),
        ('devops', 'devops'),
        ('Artificial intelligence', 'Artificial intelligence'),
        ('block chain', 'block chain'),
        ('mobile development', 'mobile development'),
        ('data structures', 'data structures'),
    )
    tag = models.CharField(max_length=30, choices=tag_choices, null=False, blank=False)
    likes = models.IntegerField(default=0)
        
class Comment(models.Model):
    comment_id = models.AutoField(primary_key=True)
    discussion_id = models.ForeignKey(Discussion, on_delete=models.CASCADE)
    user_id = models.ForeignKey(User, on_delete=models.CASCADE, to_field="username", db_column="username")
    comment = models.TextField(max_length=500, null=False, blank=False)
    # commented_date_time = models.DateTimeField(auto_now_add = True)

    def __str__(self):
        return f'{self.user_id.username}C{self.comment[:5]}'