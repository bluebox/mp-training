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


def format_currency(amount, currency_symbol='$'):
    print(f'${amount:,.2f}')
    return f'${amount:,.2f}'

amount = 1234567.896
currency_symbol = "$"
# print(format_currency(amount, currency_symbol='$'))
print("Formatted curency: ",format_currency(amount, currency_symbol='$'))