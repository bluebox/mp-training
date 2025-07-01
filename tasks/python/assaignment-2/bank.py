class BankAccount():
    def __init__(self,account_number,initial_balance):
        self.account_number = account_number
        self.__balance = initial_balance if initial_balance>=0 else 0
    def withdraw(self,amount):
        if amount<self.__balance:
            self.__balance = self.__balance-amount
            return self.__balance
        else:
            print("Insufficient balance. Available Balance amount :: ",self.get_balance())
            return None
    def deposit(self,amount):
        self.__balance += amount if amount>=0 else 0
    def get_balance(self):
        return self.__balance

account = BankAccount("Acc.01",1000)
print(account.withdraw(100))
account.deposit(100)
print(account.get_balance())
print(account.__balance)            