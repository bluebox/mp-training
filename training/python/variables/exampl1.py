class Variable:
    def __init__(self,val):
        self.data = val
    def __eq__(self, other):
        if isinstance(other,Variable):
            raise TypeError
        if self.data == other.data:
            return True
        return False
    def __hash__(self):
        return hash(self.data)
    def __str__(self):
        return str(self.data)
    def __repr__(self):
        return str(self.data)



var = Variable(10)
print(var)
varList = []
for i in range(10):
    varList.append(Variable(i))

print(varList)