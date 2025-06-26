import copy

d = {'a':10,'b':30,'d':20,'c':40}
d_sort = dict(sorted(d.items(),key=lambda z:z[1]))
print(d_sort)

l = [[1,2,3,4],[5,6,7,8,9]]

l1 = [x for i in l for x in i if x != 5]
print(l1)

dict1 = {'a':1,'b':2}
dict2 = {'x':1,'y':2}
master_dict = [dict1,dict2]

master_dict_copy = copy.deepcopy(master_dict)
print(master_dict_copy)
master_dict_copy[0]['a'] = 4
print(master_dict_copy)
print(master_dict)





# l = [1,2,3,4]
# for i in range(len(l)):
#     l.append(i)
# print(l)
#
# while(len(l) <100):
#     l.append(len(l))
# print(l)
# l = lambda a:
