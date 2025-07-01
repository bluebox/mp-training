class BankAccount:
    def __init__(self, account_number, initial_balance):
        if initial_balance <= 0:
            print("Balance should be a positive number")
        self.account_number = account_number
        self._balance = initial_balance

    def deposit(self, amount):
        if amount > 0:
            self._balance += amount
        else:
            print("Please enter positive amount")

    def withdraw(self, amount):
        if 0 < amount <= self._balance:
            self._balance -= amount
        else:
            print("Insufficient funds")

    def get_balance(self):
        return self._balance

account = BankAccount("1234567890", 5000)

print(account.account_number)
print(account.get_balance())

account.deposit(1500)
print(account.get_balance())

account.withdraw(1000)
print(account.get_balance())

account._balance = 999999
print(account._balance)
print(account.get_balance())
