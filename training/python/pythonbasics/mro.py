class A:
    def __init__(self):
        print("a")

class B(A):
    def __init__(self):
        print("b")
        super().__init__()


class C(A):
    def __init__(self):
        print("c")
        super().__init__()


class D(A):
    def __init__(self):
        print("d")
        super().__init__()


class E(B,C):
    def __init__(self):
        print("e")
        super().__init__()

class G(B,C):
    def __init__(self):
        print("g")
        super().__init__()

class F(E,G,D):
    def __init__(self):
        print("f")
        super().__init__()
        A()


f = F()
