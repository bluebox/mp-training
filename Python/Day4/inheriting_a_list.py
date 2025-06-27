class List(list):
    def append(self, value):
        print(f"{value} is appending ")
        super().append(value)


a = List()
a.append(20)
a.append(40)

for i in a:
    print(i)


class stack(list):
    def push(self, value):
        super().append(value)

    def append(self, value):
        self.push(value)

    def pop(self):
        if not self:
            print("stack is empty")
            return
        super().pop()


a = stack()
a.append(90)
a.push(80)
for i in a:
    print(i, end=' ')
