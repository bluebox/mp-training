""" Program which acts as a calculator """
while True:
    print("Enter two numbers")
    n = int(input())
    m = int(input())
    print("Enter the mathematical symbol to be entered")
    c = input()
    if c == '+':
        print(n+m)
    elif c == '-' :
        print(n-m)
    elif c == '/' :
        try:
           print(n/m)
        except ArithmeticError:
            print("Invalid input")
    elif c == '*' :
        print(n * m)
    elif c == '%' :
        print(n % m)
    elif c == '^':
        print(n ** m)
    else:
        print("Wrong Symbol enter")
    print("Want to continue press y if not then n")
    if input() == 'n':
        break
