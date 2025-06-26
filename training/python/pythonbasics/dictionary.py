from collections import OrderedDict
from typing import OrderedDict

user = OrderedDict({
    'a' : 10,
    'b' : 20
})

user2 ={
    'b' : 20,
    'a' : 10
}


print(user.get("c","not in dictionary"))

# print(list(user.items()))
print(type(user2))


# print(user == user2)
# print(type(user.keys()))
# print(type(user.values()))
# print(type(user.items()))

# a = [[1,2],[3,4]]
# b = [[1,2],[3,4]]
# print(a+b)