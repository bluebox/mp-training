class Node:
    def __init__(self, val=0):
        self.val = val
        self.next = None


class Linkedlist:
    def __init__(self):
        self.head = None

    def add(self, val):
        if self.head == None:
            self.head = Node(val)
            return
        a = self.head
        while a.next:
            a = a.next
        a.next = Node(val)

    def display(self):
        temp = self.head
        while temp:
            print(temp.val)
            temp = temp.next


a = Linkedlist()
a.add(10)
a.add(20)
a.add(30)
a.display()
