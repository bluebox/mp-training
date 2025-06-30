def invert_dictionary(d):
    new_d={}
    
    # for i in set(d.values()):                   #many iterations
    #     new_d[i]=[key for key in d if d[key]==i]
    # return new_d


    for key,value in d.items():                 #using items() reduces no.of iterations
        if value not in new_d:
            new_d[value]=[key]
        else:
            new_d[value].append(key)
    return new_d


d={'a':1,'b':2,'c':1,'d':3}
print(invert_dictionary(d))