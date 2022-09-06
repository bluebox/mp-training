from django.db import models

class professor(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.TextField(max_length=50)
    salary = models.IntegerField()
    def __str__(self):
        return "{} {} {} ".format(self.id ,self.name,self.salary)

class professor_attendance(models.Model):
    professor_id = models.ForeignKey(professor,on_delete=models.SET_NULL,null=True)
    total_leaves = models.IntegerField()
    total_working_days = models.IntegerField()
    def __str__(self):
        return "{} {} {} ".format(self.professor_id ,self.total_leaves,self.total_working_days)

class department(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.TextField(max_length=40)
    def __str__(self):
        return "{} {} ".format(self.id, self.name)

class subjects(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.TextField(max_length=40)
    professor_id = models.ForeignKey(professor,on_delete=models.CASCADE)
    semester = models.IntegerField()
    year = models.IntegerField()
    credits = models.IntegerField()


    def __str__(self):
        return "{} {} {} {} {} {} ".format(self.id ,self.name,self.professor_id,self.semester,self.year,self.credits)


class department_subjects(models.Model):
    department_id = models.ForeignKey(department,on_delete=models.SET_NULL,null=True)
    subject_id = models.ForeignKey(subjects,on_delete=models.SET_NULL,null=True)
    def __str__(self):
        return "{} {} ".format(self.department_id, self.subject_id)

class students(models.Model):
    name = models.TextField(max_length=50)
    department_id = models.ForeignKey(department,on_delete=models.CASCADE)
    student_id = models.IntegerField(primary_key=True)
    def __str__(self):
        return "{} {} {} ".format(self.student_id,self.name,self.department_id)

class student_fee_details(models.Model):
    student_id = models.ForeignKey(students,on_delete=models.SET_NULL, NULL=True)
    fee_due = models.IntegerField()
    fee_paid = models.IntegerField()
    total_fee = models.IntegerField()
    def __str__(self):
        return "{} {} {} {}".format(self.student_id,self.fee_due,self.fee_paid,self.total_fee)


class students_schedule(models.Model):
    student_id  = models.ForeignKey(professor,on_delete=models.CASCADE)
    subject_id= models.ForeignKey(subjects,on_delete=models.CASCADE)
    from_time = models.TimeField()
    to_time = models.TimeField()
    def __str__(self):
        return "{} {} {} {}".format(self.student_id,self.subject_id,self.from_time,self.to_time)



class students_attendence(models.Model):
    student_id = models.ForeignKey(students,on_delete=models.SET_NULL,null=True)
    total_absent = models.IntegerField()
    total_attendance = models.IntegerField()

    def __str__(self):
        return "{} {} {} ".format(self.student_id, self.total_absent, self.total_attendance)

