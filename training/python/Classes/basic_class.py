class Parent:
    '''This is the basic class implementation example'''

    def __init__(self,*a,**arg):
        self.l = list(a)
        self.b = dict(arg)

    def __add__(self, other):
        l1 = self.l+ other.l
        b1 = {**self.b, **other.b}
        return Parent(l1, b1)
    def __eq__(self, other):
        if self.l == other.l and self.b == other.b:
            return True
        return False
    def __str__(self):
        string_repr = "l : "+str(self.l)+"\nb : "+str(self.b)
        return string_repr

    def __repr__(self):
        return self.l,self.b

    def __hash__(self):
        return hash((tuple(self.l),tuple(sorted(self.b.items()))))

test = Parent(1, 2, 3, 4, 5, 6, a=10, b=11, c=12)
test1 = Parent(1, 2, 3, 4, 5, 6, a=10, b=11, c=12)
print(hash(test))
print(hash(test1))
print(test.__doc__)
