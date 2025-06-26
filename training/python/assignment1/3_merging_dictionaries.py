def merge_and_sum(dic1,dic2):
    new_dic = dic1.copy()
    # print(dic1)
    for key in dic2:
        # if key not in new_dic:
        #     new_dic[key] = 0
        new_dic[key] = new_dic.setdefault(key,0) + dic2[key]
    # print(dic1)
    return new_dic

d1 = {"a" : 10,"b" : 20,"c" :30}
d2 = {"b" : 5,"c" : 15,"d" :40}
dic = merge_and_sum(d1,d2)
print(dic)