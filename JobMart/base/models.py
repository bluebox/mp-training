from django.db import models
from django.contrib.auth.models import User

class Address(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name='address', null=True)
    house_no = models.CharField(max_length=100, null=True, blank=True)
    street = models.CharField(max_length=100, null=True, blank=True)
    city = models.CharField(max_length=100, null=True, blank=True)
    state = models.CharField(max_length=100, null=True, blank=True)
    zipcode = models.CharField(max_length=100, null=True, blank=True)
    country = models.CharField(max_length=100, null=True, blank=True)
    _id = models.AutoField(primary_key=True, editable=False)
    
    def __str__(self):
        return self.house_no
    
    
class Education(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name='education', null=True)
    major = models.CharField(max_length=100, null=True, blank=True)
    degree = models.CharField(max_length=100, null=True, blank=True)
    university = models.CharField(max_length=100, null=True, blank=True)
    percentage = models.DecimalField(max_digits=3, decimal_places=2, null=True, blank=True)
    _id = models.AutoField(primary_key=True, editable=False)
    
    def __str__(self):
        return self.major + " " + self.degree + " "
    

class Experience(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name='experience', null=True)
    working_present = models.BooleanField(default=False, null=True, blank=True)
    experience = models.DecimalField(max_digits=10, decimal_places=2, null=True, blank=True)
    company_name = models.CharField(max_length=100, null=True, blank=True)
    job_location = models.CharField(max_length=100, null=True, blank=True)
    description = models.CharField(max_length=300, null=True, blank=True)
    _id = models.AutoField(primary_key=True, editable=False)  
    
    def __str__(self):
        return str(self.working_present) + " " + str(self.experience) + " " + str(self.company_name)


class Skill(models.Model):
    skill_name = models.CharField(max_length=100, null=True, blank=True)
    _id = models.AutoField(primary_key=True, editable=False)
    
    def __str__(self):
        return str(self.skill_name)
    

class SkillDetails(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name='skills', null=True)
    skills = models.ManyToManyField(Skill)
    _id = models.AutoField(primary_key=True, editable=False)
    
    def __str__(self):
        return str(len(self.skills.all())) + ' skills'
    
    
class Company(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, null=True)
    company_name = models.CharField(max_length=100, null=True, blank=True)
    industry_type = models.CharField(max_length=100, null=True, blank=True)
    description = models.TextField(null=True, blank=True)
    Established = models.DateField(null=True, blank=True)
    image = models.ImageField(null=True, blank=True)
    _id = models.AutoField(primary_key=True, editable=False)
    
    def __str__(self):
        return self.company_name
    

class JobPost(models.Model):
    company = models.ForeignKey(Company, on_delete=models.CASCADE, null=True)
    location = models.CharField(max_length=100, null=True, blank=True)
    job_type = models.CharField(max_length=100, null=True, blank=True)
    skills_required = models.CharField(max_length=100, null=True, blank=True)
    role = models.CharField(max_length=100, null=True, blank=True)
    job_description = models.TextField(null=True, blank=True)
    posted_on = models.DateField(auto_now_add=True)
    experience = models.IntegerField(default=0)
    education = models.CharField(max_length=240, null=True, blank=True)
    department = models.CharField(max_length=100, null=True, blank=True)
    role_category = models.CharField(max_length=100, null=True, blank=True)
    
    def __str__(self):
        return self.role
    

class JobApplications(models.Model):
    job_post = models.ForeignKey(JobPost, on_delete=models.CASCADE, null=True)
    applicant = models.ForeignKey(User, on_delete=models.CASCADE, null=True)
    resume = models.FileField(upload_to='candidate_resume/', null=True, blank=True)



    
    