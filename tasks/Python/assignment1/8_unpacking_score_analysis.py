def process_scores(scores):
    if len(scores)>=2:
        first,*middle,last = scores
        length = len(middle)
        avg_middle = sum(middle) / length if length!=0 else 0
        return first,last,avg_middle
    return "scores must have at least length 2"

scores1 = [98, 77, 68, 67, 71, 70, 86, 93]
scores2 = [77]
print(process_scores(scores1))
print(process_scores(scores2))