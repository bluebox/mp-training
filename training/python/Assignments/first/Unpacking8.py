def process_scores(scores):
    if len(scores)==2:
        first,last=scores
        score_tuple=(first,last,0)
    else:
        first,*middle,last=scores
        score_tuple=(first,last,sum(middle)/len(middle))
    return score_tuple


scores=[98,85,88,92,78]
print(process_scores(scores))