
import re
from .models import StudentDetails, Branch, Subjects, Semester


class FormBranch:
    def __init__(self, request):
        self.name = request.POST.get('fullname')
        self.validation_errors = {
            'name': []
        }

    def is_valid(self):
        ans = True
        if len(self.name) < 4:
            self.validation_errors['name'].append('Branch must be greater than or equal to 4 Characters!')
            ans = False
        return ans

    def save(self):
        Branch(name=self.name).save()


class SaveMarks:
    def __init__(self, request):
        self.sub1 = request.POST.get('sub1')
        self.sub2 = request.POST.get('sub2')
        self.sub3 = request.POST.get('sub3')
        self.sub4 = request.POST.get('sub4')
        self.validation_error = {
            'sub1': [],
            'sub2': [],
            'sub3': [],
            'sub4': [],
        }

    def marks_constraint(self, marks, sub):
        if marks:
            marks = int(marks)
            if marks < 0 or marks > 100:
                self.validation_error[sub].append('Marks must be in between 1-100!')
                return False
            return True

    def is_valid(self):
        ans = True
        if not self.marks_constraint(self.sub1, "sub1"):
            ans = False
        if not self.marks_constraint(self.sub2, "sub2"):
            ans = False
        if not self.marks_constraint(self.sub3, "sub3"):
            ans = False
        if not self.marks_constraint(self.sub4, "sub4"):
            ans = False
        return ans


class StudentValidation:
    def __init__(self, request):
        self.std_id = request.POST.get('std_id')
        self.validation_error = {
            "std_id": [],
        }

    def is_valid(self):
        if len(self.std_id) == 0:
            self.validation_error['std_id'].append('Enter some Student Id!')
            return False
        try:
            student = StudentDetails.objects.get(id=int(self.std_id))
            if student.sem_id.id > 4:
                self.validation_error['std_id'].append('Student has completed all Semesters!')
                return False
        except StudentDetails.DoesNotExist:
            self.validation_error['std_id'].append('Not a valid Student Id!')
            return False
        return True


class SubjectValidation:
    def __init__(self, request):
        self.branch_id = request.POST.get('branch_id')
        self.sem_id = request.POST.get('sem_id')
        self.sub1 = request.POST.get('sub1')
        self.sub2 = request.POST.get('sub2')
        self.sub3 = request.POST.get('sub3')
        self.sub4 = request.POST.get('sub4')
        self.validation_error = {
            'branch_id': [],
            'sem_id': [],
            'sub1': [],
            'sub2': [],
            'sub3': [],
            'sub4': [],
        }

    def is_valid(self):
        ans = True
        if int(self.branch_id) <= 0:
            self.validation_error['branch_id'].append('Not a valid Branch.')
            ans = False
        if 1 > int(self.sem_id) or int(self.sem_id) > 4:
            self.validation_error['sem_id'].append('Not a valid Semester.')
            ans = False
        if len(re.findall('[!@`~#$%^&*+<>?=]', str(self.sub1))) > 0:
            self.validation_error['sub1'].append('Can\'t use special characters in subject name')
            ans = False
        elif len(self.sub1) == 0:
            self.validation_error['sub1'].append('Can\'t pass empty string!')
            ans = False
        if len(re.findall('[!@`~#$%^&*+<>?=]', str(self.sub2))) > 0:
            self.validation_error['sub2'].append('Can\'t use special characters in subject name')
            ans = False
        elif len(self.sub2) == 0:
            self.validation_error['sub2'].append('Can\'t pass empty string!')
            ans = False
        if len(re.findall('[!@`~#$%^&*+<>?=]', str(self.sub3))) > 0:
            self.validation_error['sub3'].append('Can\'t use special characters in subject name')
            ans = False
        elif len(self.sub3) == 0:
            self.validation_error['sub3'].append('Can\'t pass empty string!')
            ans = False
        if len(re.findall('[!@`~#$%^&*+<>?=]', str(self.sub4))) > 0:
            self.validation_error['sub4'].append('Can\'t use special characters in subject name')
        elif len(self.sub4) == 0:
            self.validation_error['sub4'].append('Can\'t pass empty string!')
            ans = False

        return ans

    def save(self):
        branch = Branch.objects.get(id=self.branch_id)
        sem = Semester.objects.get(id=self.sem_id)
        Subjects(name=self.sub1, branch_id=branch, sem_id=sem).save()
        Subjects(name=self.sub2, branch_id=branch, sem_id=sem).save()
        Subjects(name=self.sub3, branch_id=branch, sem_id=sem).save()
        Subjects(name=self.sub4, branch_id=branch, sem_id=sem).save()


class StudentDetailsValidation:
    def __init__(self, request):
        self.name = request.POST.get('name')
        self.phone = request.POST.get('phone')
        self.address = request.POST.get('address')
        self.gender = request.POST.get('gender')
        self.branch_id = request.POST.get('branch')
        self.validation_error = {
            "name": [],
            "phone": [],
            "address": [],
            "gender": [],
            "branch_id": [],
        }

    def is_valid(self):
        ans = True
        if len(re.findall('[0-9!@)({}.,/:;"\'|\\`~#$%^&*+<>?=]', self.name)) > 0:
            self.validation_error['name'].append('Not a valid name! (Special Characters are not allowed)')
            ans = False
        elif len(self.name) == 0:
            self.validation_error['name'].append('Name Can\'t be empty')
            ans = False
        if len(re.findall('[a-zA-Z!@)({}.,/:;"\'|\\`~#$%^&*+<>?=]', self.phone)):
            self.validation_error['name'].append('Special Characters and Alphabets are not allowed.')
            ans = False
        if len(str(self.phone)) != 10:
            self.validation_error['phone'].append('Phone number must of 10 digits!')
            ans = False
        elif int(self.phone) < 0:
            self.validation_error['phone'].append('hyphen not allowed in phone number!')
            ans = False
        if self.gender == 'NA':
            self.validation_error['gender'].append('Please select a Gender!')
            ans = False
        if int(self.branch_id) == 0:
            self.validation_error['branch_id'].append('Please select a Branch!')
            ans = False
        if len(self.address) == 0:
            self.validation_error['address'].append('Please fill residential details.')
            ans = False
        return ans

    def save(self):
        branch = Branch.objects.get(id=self.branch_id)
        sem = Semester.objects.get(id=1)
        StudentDetails(name=self.name, phone=self.phone, address=self.address, gender=self.gender, branch_id=branch, sem_id=sem).save()


class SearchValidation:
    def __init__(self, request):
        self.std_id = request.POST.get('std_id')
        self.validation_error = {
            "std_id": [],
        }

    def is_valid(self):
        if len(self.std_id) == 0:
            self.validation_error['std_id'].append('Enter some Student Id!')
            return False
        try:
            student = StudentDetails.objects.get(id=int(self.std_id))
            return True
        except StudentDetails.DoesNotExist:
            self.validation_error['std_id'].append('Not a valid Student Id!')
            return False


class FilterValidation:
    def __init__(self, request, entity):
        self.entity = request.POST.get(entity)
        self.entity_name = entity
        self.validation_error = {
            'filter': []
        }

    def is_valid(self):
        if self.entity == 0:
            self.validation_error['filter'].append('Please select a valid ' + self.entity_name)
            return False
        return True
