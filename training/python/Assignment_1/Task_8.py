#
#  Unpacking with *rest for Score Analysis :
#
# Problem Statement: Write a function process_scores(scores) that takes a list of numerical scores.
# The list is guaranteed to have at least two elements. The function should use list unpacking
# with the * operator to separate the first score, the last score, and all the scores in between.
# Requirements:
# Must use list unpacking in a single assignment statement to capture the first score, the middle scores (*rest), and the last score.

# The function should calculate the average of the middle scores. If there are no middle scores
# (i.e., the input list has only two elements), the average should be considered 0.
# The function must return a tuple containing three elements: the first score, the last score, and the average of the middle scores.

# Input: scores = [98, 85, 88, 92, 78]
# Unpacking: first = 98, middle = [85, 88, 92], last = 78
# Average of middle: (85 + 88 + 92) / 3 = 88.33...
# Expected Output: (98, 78, 88.333...)

def process_scores(scores):
    report=[]
    first,*middle,last=scores
    avg=sum(middle)/(len(scores)-2)
    report.extend([first,last,round(avg,2)])
    return tuple(report)

scores = [98, 85, 88, 92, 78]
print(process_scores(scores))