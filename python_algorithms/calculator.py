"""Calculator Module"""


def add(num1, num2):
    """
    addition
    :param num1: first number
    :param num2: second number
    :return: addition
    """
    return num1+num2


def subtract(num1, num2):
    """
    subtraction
    :param num1: first number
    :param num2: second number
    :return: subtraction
    """
    return num1 - num2


def multiply(num1, num2):
    """
    multiply
    :param num1: first number
    :param num2: second number
    :return: multiplication
    """
    return num1 * num2


def divide(num1, num2):
    """
    division
    :param num1: first number
    :param num2: second number
    :return: division
    """
    return num1 / num2


while True:
    print('Press 1 for addition: ')
    print('Press 2 for subtraction:')
    print('Press 3 for multiplication:')
    print('Press 4 for division:')
    print('Press 5 for EXIT:')
    op = int(input('Choose option: '))
    if op == 5:
        break

    ans = 0
    n1 = int(input('Enter first number: '))
    n2 = int(input('Enter second number: '))
    if op == 1:
        ans = add(n1, n2)
    elif op == 2:
        ans = subtract(n1, n2)
    elif op == 3:
        ans = multiply(n1, n2)
    else:
        ans = divide(n1, n2)

    print(ans)
