from codecs import getencoder
from re import T
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

    def __str__(self):
        return self.user.username

@receiver(post_save, sender=User)
def create_user_profile(sender, instance, created, **kwargs):
    print(kwargs)
    if created:
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
    description = models.TextField(max_length=500, unique=True, blank=False, null=False)
    hints = models.TextField(max_length=50, blank=False, null=False)
    test_cases = models.TextField(max_length=500, blank=False, null=False)
    likes = models.IntegerField(default=0)
    dislikes = models.IntegerField(default=0)
    difficulty_level_options = (
        ('E', 'easy'), 
        ('M', 'medium'),
        ('H', 'hard'),
    )
    difficulty_level = models.CharField(max_length=1, choices=difficulty_level_options, blank=False, default='E', null=False)

    def __str__(self):
        name = "_".join(self.problem_name.split(" "))
        return name

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
        (2, 'Wrong Answer'),
        (3, 'Time Limit Exceeded'),
        (4, 'Input Limit Exceeded'),
        (5, 'Not Attempted')
    )
    status = models.IntegerField(choices=status_choices, null=False, blank=False)

    def __str__(self):
        return f'{self.user_id.username}_P_{self.problem_id.__str__()}'

class Discussion(models.Model):
    discussion_id = models.AutoField(primary_key=True)
    user_id = models.ForeignKey(User, on_delete=models.DO_NOTHING, null=True)
    problem_id = models.ForeignKey(Problem, on_delete=models.DO_NOTHING, null=True)
    title = models.CharField(max_length=30, null=False, blank=False)
    discussion = models.TextField(max_length=500, null=True, blank=True)
    created_date_time = models.DateTimeField(auto_now_add=True, null=False, blank=False)
    upvotes = models.IntegerField(default=0, null=False, blank=False)
    downvotes = models.IntegerField(default=0, null=False, blank=False)

    def __str__(self):
        return self.title

class Comment(models.Model):
    comment_id = models.AutoField(primary_key=True)
    discussion_id = models.ForeignKey(Discussion, on_delete=models.CASCADE)
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)
    comment = models.TextField(max_length=500, null=False, blank=False)

    def __str__(self):
        return f'{self.user_id.username}C{self.comment[:5]}'