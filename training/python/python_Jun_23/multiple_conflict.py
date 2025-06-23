class A:
    def speak(self):
        print("A")

class B(A):
    def speak(self):
        print("B")

class C(A):
    def speak(self):
        print("C")

class D(B, C):
    pass

d = D()
d.speak()
