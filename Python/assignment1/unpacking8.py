def process_scores(scores):
    if len(scores) <= 2:
        a, b = scores
        return (a, b)
    first,*rest,last=scores
    rest=(sum(rest)/len(rest))
    return (first,last,rest)

scores = [98, 85, 88, 92, 78]
val = process_scores(scores)
print(val)
