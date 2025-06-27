# Thousand-Separator Currency Formatter :
#
# Problem Statement: Write a function format_currency(amount, currency_symbol='$')
# that takes a floating-point number and returns it as a string formatted for currency display.
#
# Requirements:
# The number must be rounded to exactly two decimal places.
# A comma must be used as a thousands separator.
# The string must be prefixed with the given currency_symbol.
# You must use an f-string with format specifiers to achieve this.

def recursive_format(num):
    if len(num) <= 3 :
        return num
    return recursive_format(num[:-3])+','+num[-3:]


def format_currency(amount, currency ):

    amt=str(round(amount,2))
    print(amt)
    lst=amt.split(".")
    #recursive solution
    num_r=lst[0]
    print("Formatted using recursion "+"$"+recursive_format(num_r)+"."+lst[1])

    #Iterative solution
    num=list(lst[0])
    count=0
    for i in range(len(num)-2,1,-3):
         num.insert( i-1 , ',')
         if count == 3 :
              num.insert( i , "," )
              count = 0

    print("Iteratively formatted " + currency + "".join(num) + "." + lst[1])

    return f'${amount:,.2f}'

amount = 73421342143.896
currency_symbol = "$"
print("Formatted curency: ",format_currency(amount, currency_symbol ))