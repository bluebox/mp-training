class BankAccount:
    def __init__(self,acno,bal):
        self.acno=acno
        self.__bal=bal
    def deposit(self,amount):
        self.__bal+=amount
    def withdraw(self,amount):
        if self.__bal>amount:
            self.__bal-=amount
        else:
            print("no sufficient funds")
    def get_bal(self):
        return self.__bal

b=BankAccount(100,1000)
print("current bal: ",b.get_bal())
b.deposit(500)
print("current bal: ",b.get_bal())
b.withdraw(600)
print("current bal: ",b.get_bal())
b.withdraw(1000)
print("current bal: ",b.get_bal())
# b.__bal+=500
print(b.acno)




