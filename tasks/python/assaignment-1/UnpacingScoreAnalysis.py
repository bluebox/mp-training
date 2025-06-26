def process_scores(scores):
    first,*middle,last = scores
    avg = 0
    if len(middle) != 0:
        avg = sum(middle)/len(middle)
    return first,last,round(avg,2)

print(process_scores([98,85,2,88,92,78]))