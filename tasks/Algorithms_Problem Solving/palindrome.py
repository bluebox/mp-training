"""Program for palindrome number"""


def palin(num):
    """function for checking palindrome"""
    num1 = num[::-1]
    if num == num1:
        print("Number is palindrome")
    else:
        print("Number is not palindrome")


n = input("Enter a number\n")
palin(n)
