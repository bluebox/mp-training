def process_scores(scores):
    if len(scores) < 2:
        return "List length should be greater than 2"
    first, *middle, last = scores
    summation = 0
    if len(middle) != 0:
        for i in middle:
            summation+=i
        avg = summation/len(middle)
        to_be_returned = (first, last, avg)
        return  to_be_returned
    to_be_returned = (first, last, summation)
    return to_be_returned

li = [98, 85, 88, 92, 78]
li2 = [98, 78]
li3 = [0]

print(process_scores(li))
print(process_scores(li2))
print(process_scores(li3))