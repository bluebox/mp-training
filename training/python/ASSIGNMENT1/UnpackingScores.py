def process_scores(scores):
    a,*b,c=scores
    average=sum(b)/(len(b))
    return (a,c,average)

scores = [98, 85, 88, 92, 78]
print(process_scores(scores))