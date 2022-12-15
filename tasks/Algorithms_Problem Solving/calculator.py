"""Simple Calculator"""


def add(x_num, y_num):
    """This return summation of two numbers"""
    return x_num + y_num


def sub(x_num, y_num):
    """This return difference of two numbers"""
    return x_num - y_num


def mul(x_num, y_num):
    """This return multiplication of two numbers"""
    return x_num * y_num


def div(x_num, y_num):
    """This return quotient of first number when divided by_num second number"""
    return x_num / y_num


print('Select\n')
print('1.Addition\n')
print('2.Subtraction\n')
print('3.Multiplication\n')
print('4.Division\n')

while True:
    # take input from the user
    choice = input("Enter choice(1/2/3/4): ")

    # check if choice is one of the four options
    if choice in ('1', '2', '3', '4'):
        num1 = float(input("Enter first number: "))
        num2 = float(input("Enter second number: "))

        if choice == '1':
            print(num1, "+", num2, "=", add(num1, num2))

        elif choice == '2':
            print(num1, "-", num2, "=", sub(num1, num2))

        elif choice == '3':
            print(num1, "*", num2, "=", mul(num1, num2))

        elif choice == '4':
            print(num1, "/", num2, "=", div(num1, num2))

        # check if user wants another calculation
        # break the while loop if answer is no
        next_calculation = input("Let's do next calculation? (yes/no): ")
        if next_calculation == "no":
            break

    else:
        print("Invalid Input")
