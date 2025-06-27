from copy import copy,deepcopy
from collections.abc import Iterable

class Variable:
    def __init__(self,val):
        self.data = val
    def __eq__(self, other):
        if not isinstance(other,Variable):
            raise TypeError
        if self.data == other.data:
            return True
        return False
    def __getitem__(self, index):
        return self.data[index]
    def __setitem__(self, key, value):
        self.data[key] = value
    def __hash__(self):
        return hash(tuple(self.data))
    def __str__(self):
        return str(self.data)
    def __repr__(self):
        return str(self.data)
    def __copy__(self):
        print("in copy magic func: ",self.data)
        cls = self.__class__
        res = cls.__new__(cls)
        print(type(self.__dict__))
        res.__dict__.update(self.__dict__)
        return res
    def __deepcopy__(self, memo):
        print("in deep copy magic function",self.data)
        cls = self.__class__
        res = cls.__new__(cls)
        memo[id(self)] = res
        for k,v in self.__dict__.items():
            res.__dict__[k] = deepcopy(v,memo)
        return res
    def __iter__(self):
        return iter(self.data) if isinstance(self.data,Iterable) else None
    # def __next__(self):
    #


var = Variable(10)
print(var)
varList = []
for i in range(10):
    varList.append(Variable(i))
var1 = copy(var)
var2 = deepcopy(var)
var3 = Variable([1,[2,3,4],5,6])
var4 = copy(var3)
var5 = deepcopy(var4)
var3[1][0] = 4
print(var4)
print(var5)

# for i in var4:
#     print(i)
