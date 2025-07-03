a = 10

class Parent:
    global a
    @staticmethod
    def display():
        print(a)


Parent.display()

print(globals())