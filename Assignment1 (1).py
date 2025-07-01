#!/usr/bin/env python
# coding: utf-8

# In[ ]:


#1
final=[]
def get_student_info(n):
    for i in range(n):
        data=input("enter studentid and name ")
        l=data.split(" ")
        scores=input(f"enter scores {l[1]}")
        s=tuple(map(int,scores.split()))
        l.append(s)
        final.append(tuple(l))
    return final
l=get_student_info(2)
print("harcoded list",l)
for i in final:
    student_id,name,scores=i
    s1,s2,s3=scores
    print(f"name: {name} average_score: {(s1+s2+s3)/3}")


# In[1]:


#2
def group_by_key(tuples_list):
    d=dict()
    for tup in l:
        if tup[0] in d:
            d[tup[0]].append(tup[1])
        else:
            d.update({tup[0]:[tup[1]]})
    return d
l=[("a",1),("b",2),("c",3),("a",4)]
print(group_by_key(l))
        


# In[3]:


#3
def merge_and_sum(dict1, dict2):
    new_dict=dict()
    for i,j in dict1.items():
            new_dict[i]=j
    for i in dict2.items():
        if i[0] in new_dict:
            new_dict.update({i[0]:dict1[i[0]]+i[1]})
        else:
            new_dict[i[0]]=i[1]
    return new_dict
            
dict1={"a":1,"b":2,"e":3}
dict2={"e":34,"a":3,"b":7}
print(merge_and_sum(dict1, dict2))


# In[4]:


# 4
def invert_dictionary(d):
    new_dict=dict()
    for i in d.items():
        if i[1] in new_dict:
            new_dict[i[1]].append(i[0])
        else:
            new_dict.update({i[1]:[i[0]]})
    return new_dict
d={'a': 1, 'b': 2, 'c': 1, 'd': 2}
print(invert_dictionary(d))


# In[5]:


# 5
def create_or_update_item(items_dict, key, value=None):
    if value is not None:
        if key in items_dict:
            items_dict[key]=value
        else:
            items_dict.update({key:value})
    else:
        if key in items_dict:
            items_dict.pop(key)
    return items_dict
items_dict={
    "med":"plus",
    "optival":"solutions",
    "hello":"world",
    "apple":None,
}
print(create_or_update_item(items_dict, "bat", value="man"))
print(create_or_update_item(items_dict, "hello", value="universe"))
print(create_or_update_item(items_dict, "apple", value=None))


# In[8]:


# 6
def find_common_elements(list1, list2):
    new_list=[i for i in list1 if i in list2]
    return new_list

list1=[1,2,3,4]
list2=[3,4,5,6]
print(find_common_elements(list1, list2))


# In[9]:


# 7
def flatten_list(nested_list):
    new_list=[i[j] for i in nested_list for j in range(0,len(i))]
    return new_list
nested_list=[[1,23,34],[25,56,98],[23,45,5]]
print(flatten_list(nested_list))


# In[11]:


# 8
def process_scores(scores):
#     unpacking
    first,*rest,last=scores
    avg=0
    if len(rest)>0:
        avg=sum(rest)/len(rest)
    return (first,last,avg)
# scores = [98, 85, 88, 92, 78]
scores=[4,5]
print(process_scores(scores))

        


# In[17]:


# 9
def is_palindrome_slicing(data): 
    if data==data[::-1]:
        return True
    return False
data=[1,'2',1,2,1.0]
if is_palindrome_slicing(data):
    print("palindrome")
else:
    print("not a palindrome")


# In[21]:


# 10
def format_currency(amount, currency_symbol='$'):
    amt=f"{amount:,.2f}"
    return f"{currency_symbol}{amt}"

format_currency(1234567.91, currency_symbol='$')
    


# In[22]:


# 11
def process_names(names):
    new_names=list(map(lambda x:x.capitalize(),names))
    new_filtered=list(filter(lambda x:len(x)>3,new_names))
    return new_filtered
names = ["adaM", "grace", "charles", "tim", "alan"]
process_names(names)


# In[28]:


# 12
def convert_to_custom_base(decimal_num, base):
    if base==2:
        convert(decimal_num,2)
    elif base==8:
        convert(decimal_num,8)
    elif base==16:
        convert(decimal_num,16)
def convert(n,base):
    d={10:'A',11:'B',12:'C',13:'D',14:'E',15:'F'}
    s=""
    while(n>0):
        rem=n%base
        if rem in d:
            s+=d[rem]
        else:
            s+=str(rem)
        n=n//base
    print(s[::-1])
decimal_num=int(input())
base=int(input())
convert_to_custom_base(decimal_num, base)
        


# In[25]:


245%16

