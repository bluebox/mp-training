from collections import defaultdict

def group_by_key(tuples):
    group=defaultdict(list)

    for key,value in tuples:
        group[key].append(value)

    print(dict(group))
input=[(1,'a'),(2,'b'),(3,'c'),(1,'d'),(5,'e'),
       (1,'f'),(2,'g'),(3,'h'),(1,'i'),(5,'j'),
       (1,'k'),(2,'l'),(3,'m'),(1,'n'),(5,'o')]
group_by_key(input)