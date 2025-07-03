class BankAccount:
    def __init__(self, account_number, initial_balance):
        self.account_number = account_number
        self.__balance = initial_balance

    def deposit(self, amount):
        if amount > 0:
            self.__balance += amount
        else:
            print("amount must be positive.")

    def withdraw(self, amount):
        if 0 < amount <= self.__balance:
            self.__balance -= amount
        else:
            print("Insufficient funds.")

    def get_balance(self):
        return self.__balance

account = BankAccount("123456", 1000)

print("Current Balance:", account.get_balance())
account.deposit(500)
print("Current Balance:", account.get_balance())
account.withdraw(2000)
print("Current Balance:", account.get_balance())
# account._BankAccount__balance