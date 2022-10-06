from django.contrib.auth.models import User
from home.models import *


class problemLogic:
    def get_accuracy(problem_id):
        solved = Solved.objects.filter(problem_id=problem_id)
        correct = 0
        for query in solved:
            if query.status == 1:
                correct += 1
        accuracy = (correct / len(solved)) * 100
        return accuracy