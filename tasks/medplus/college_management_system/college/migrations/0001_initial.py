# Generated by Django 4.0.5 on 2022-09-06 05:48

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='department',
            fields=[
                ('id', models.IntegerField(primary_key=True, serialize=False)),
                ('name', models.CharField(max_length=40)),
            ],
        ),
        migrations.CreateModel(
            name='professor',
            fields=[
                ('id', models.IntegerField(primary_key=True, serialize=False)),
                ('name', models.CharField(max_length=50)),
                ('salary', models.IntegerField()),
            ],
        ),
        migrations.CreateModel(
            name='students',
            fields=[
                ('name', models.CharField(max_length=50)),
                ('student_id', models.IntegerField(primary_key=True, serialize=False)),
                ('department_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='college.department')),
            ],
        ),
        migrations.CreateModel(
            name='subjects',
            fields=[
                ('id', models.IntegerField(primary_key=True, serialize=False)),
                ('name', models.CharField(max_length=40)),
                ('semester', models.IntegerField()),
                ('year', models.IntegerField()),
                ('credits', models.IntegerField()),
                ('professor_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='college.professor')),
            ],
        ),
        migrations.CreateModel(
            name='students_schedule',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('from_time', models.TimeField()),
                ('to_time', models.TimeField()),
                ('student_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='college.professor')),
                ('subject_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='college.subjects')),
            ],
        ),
        migrations.CreateModel(
            name='students_attendence',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('total_absent', models.IntegerField()),
                ('total_attendance', models.IntegerField()),
                ('student_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='college.students')),
            ],
        ),
        migrations.CreateModel(
            name='student_fee_details',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('fee_due', models.IntegerField()),
                ('fee_paid', models.IntegerField()),
                ('total_fee', models.IntegerField()),
                ('student_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='college.students')),
            ],
        ),
        migrations.CreateModel(
            name='professor_attendance',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('total_leaves', models.IntegerField()),
                ('total_working_days', models.IntegerField()),
                ('professor_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='college.professor')),
            ],
        ),
        migrations.CreateModel(
            name='department_subjects',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('department_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='college.department')),
                ('subject_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='college.subjects')),
            ],
        ),
    ]