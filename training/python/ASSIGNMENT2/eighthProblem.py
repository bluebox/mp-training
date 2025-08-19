class BankAccount:
    def __init__(self,account_number,balance=0):
        self.__balance=balance
        self.account_number=account_number
    def deposite(self,amount):
        self.__balance+=amount
    def withdraw(self,amount):
        if amount<=self.__balance:
            self.__balance-=amount
        else:
            print("insuffitient funds")
    def getBalance(self):
        return self.__balance

a=BankAccount(1234,1000)
a.deposite(500)
a.__balance=100000
print(a.getBalance())
a.deposite(500)
print(a.getBalance())
a.deposite(500)
print(a.getBalance())
a.withdraw(3000)
a.withdraw(500)
print(a.getBalance())
a.withdraw(500)
print(a.getBalance())
a.withdraw(1000)
print(a.getBalance())
a.withdraw(500)
print(a.getBalance())
a.withdraw(500)
print(a.getBalance())

print(a.__balance)