from django.db import models

# Create your models here.
# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class AuthGroup(models.Model):
    name = models.CharField(unique=True, max_length=150)

    class Meta:
        managed = False
        db_table = 'auth_group'


class AuthGroupPermissions(models.Model):
    id = models.BigAutoField(primary_key=True)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)
    permission = models.ForeignKey('AuthPermission', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_group_permissions'
        unique_together = (('group', 'permission'),)


class AuthPermission(models.Model):
    name = models.CharField(max_length=255)
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING)
    codename = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'auth_permission'
        unique_together = (('content_type', 'codename'),)


class AuthUser(models.Model):
    password = models.CharField(max_length=128)
    last_login = models.DateTimeField(blank=True, null=True)
    is_superuser = models.IntegerField()
    username = models.CharField(unique=True, max_length=150)
    first_name = models.CharField(max_length=150)
    last_name = models.CharField(max_length=150)
    email = models.CharField(max_length=254)
    is_staff = models.IntegerField()
    is_active = models.IntegerField()
    date_joined = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'auth_user'


class AuthUserGroups(models.Model):
    id = models.BigAutoField(primary_key=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_groups'
        unique_together = (('user', 'group'),)


class AuthUserUserPermissions(models.Model):
    id = models.BigAutoField(primary_key=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    permission = models.ForeignKey(AuthPermission, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_user_permissions'
        unique_together = (('user', 'permission'),)


class DjangoAdminLog(models.Model):
    action_time = models.DateTimeField()
    object_id = models.TextField(blank=True, null=True)
    object_repr = models.CharField(max_length=200)
    action_flag = models.PositiveSmallIntegerField()
    change_message = models.TextField()
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING, blank=True, null=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'django_admin_log'


class DjangoContentType(models.Model):
    app_label = models.CharField(max_length=100)
    model = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'django_content_type'
        unique_together = (('app_label', 'model'),)


class DjangoMigrations(models.Model):
    id = models.BigAutoField(primary_key=True)
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'


class DjangoSession(models.Model):
    session_key = models.CharField(primary_key=True, max_length=40)
    session_data = models.TextField()
    expire_date = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_session'


class FacultyPassword(models.Model):
    username = models.CharField(primary_key=True, max_length=255)
    password = models.CharField(max_length=20, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'faculty_password'


class MembersMembers(models.Model):
    id = models.BigAutoField(primary_key=True)

    class Meta:
        managed = False
        db_table = 'members_members'


class StudentPassword(models.Model):
    username = models.OneToOneField('StudentsList', models.DO_NOTHING, db_column='username', primary_key=True)
    password = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'student_password'


class StudentsList(models.Model):
    rollno = models.CharField(db_column='Rollno', primary_key=True, max_length=255)  # Field name made lowercase.
    student_name = models.CharField(db_column='Student_name', max_length=255, blank=True, null=True)  # Field name made lowercase.
    dept = models.CharField(db_column='Dept', max_length=255, blank=True, null=True)  # Field name made lowercase.
    sub1 = models.IntegerField(db_column='Sub1', blank=True, null=True)  # Field name made lowercase.
    sub2 = models.IntegerField(db_column='Sub2', blank=True, null=True)  # Field name made lowercase.
    sub3 = models.IntegerField(db_column='Sub3', blank=True, null=True)  # Field name made lowercase.
    sub4 = models.IntegerField(db_column='Sub4', blank=True, null=True)  # Field name made lowercase.
    sub5 = models.IntegerField(db_column='Sub5', blank=True, null=True)  # Field name made lowercase.
    sub6 = models.IntegerField(db_column='Sub6', blank=True, null=True)  # Field name made lowercase.
    sub7 = models.IntegerField(db_column='Sub7', blank=True, null=True)  # Field name made lowercase.
    sub8 = models.IntegerField(db_column='Sub8', blank=True, null=True)  # Field name made lowercase.
    sub9 = models.IntegerField(db_column='Sub9', blank=True, null=True)  # Field name made lowercase.
    sub10 = models.IntegerField(db_column='Sub10', blank=True, null=True)  # Field name made lowercase.
    sub11 = models.IntegerField(db_column='Sub11', blank=True, null=True)  # Field name made lowercase.
    sub12 = models.IntegerField(db_column='Sub12', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'students_list'
