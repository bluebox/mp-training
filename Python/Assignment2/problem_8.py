class BankAccount:
    def __init__(self, account_number, balance=0):
        self.account_number = account_number
        self.__balance = balance

    def deposit(self, amount=0):
        self.__balance += amount

    def withdrawal(self, amount=0):
        if self.__balance >= amount:
            self.__balance -= amount
            print("withdrawal success")
        else:
            print("insufficient funds")

    def get_balance(self):
        return self.__balance


sbi = BankAccount(123, 10000)
sbi.withdrawal(100000)
# print(BankAccount.__balance)