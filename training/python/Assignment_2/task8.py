"""
Problem Statement : 
Design a BankAccount class.
The __init__ method should take an account_number and an initial balance. The account_number should be a public attribute,
but the balance should be a private attribute (use a convention like _balance or __balance).
Implement deposit and withdraw methods.
The deposit method should add an amount to the balance.
The withdrawal method should subtract an amount from the balance, but only if there are sufficient funds.
Add a get_balance method to retrieve the current balance.
Demonstrate that you cannot directly modify the private _balance attribute from outside the class.
"""
class BankAccount:
    def __init__(self, account_number, initial_balance=0):
        self.account_number = account_number
        self.__balance = initial_balance
    def deposit(self, amount):
        if amount > 0:
            self.__balance += amount
            print(f"Deposited: {amount}. New balance: {self.get_balance()}")
        else:
            print("Deposit amount must be positive.")

    def withdraw(self, amount):
        if amount > 0 and amount <= self.__balance:
            self.__balance -= amount
            print(f"Withdrew: {amount}. New balance: {self.get_balance()}")
        elif amount > self.__balance:
            print("Insufficient funds for withdrawal.")
        else:
            print("Withdrawal amount must be positive.")

    def get_balance(self):
        print("Current balance:", self.__balance)
        return self.__balance


account = BankAccount("123456789", 1000)
account.deposit(500)
account.get_balance()

account.__balance = 5000
account.get_balance()

account.get_balance()