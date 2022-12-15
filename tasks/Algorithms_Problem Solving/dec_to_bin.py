"""Program to convert Decimal Number to Binary Number"""


def dec_to_bin(inp_num):
    """
    param inp_num: Takes the number to be converted
    prints the convert decimal number
    """
    if inp_num > 1:
        dec_to_bin(inp_num // 2)
    print(f'{inp_num % 2}', end='')


def short_dec_to_bin(inp_num):
    """Short-Cut to convert dec to bin"""
    print(bin(inp_num).replace("0b", ""))


number = int(input('Enter a binary number\n'))
dec_to_bin(number)
print('\n')
short_dec_to_bin(number)
