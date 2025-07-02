class test:
    pass


print(type(test))

def add_attribute(self):
    self.z=9
a = type('anand', (), {"add":add_attribute})  # this is a class creation step  () this acts a inheritence and {} this can have attributes
b=a()
b.x=10
b.name='anand'
print(b.x)
print(b.name)
b.add()
print(b.z)
#manual metaclass

class Meta(type):
    def __new__(cls,class_name, bases,attrs):
        print(attrs)
        val={}
        for key,value in attrs.items():
            if key.startswith('__'):
                val[key]=value
            else:
                val[key.upper()]=value
        print(val)
        return type(class_name,bases,val)

class Dog(metaclass=Meta):
    x=10
    y=20
    def hello(self):
        print("hello")

a=Dog()
print(a.X)

a.HELLO()