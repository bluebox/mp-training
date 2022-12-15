"""Module to Convert Decimal to Binary"""


def dec_to_bin(dec_num, value):
    """
    decimal to binary conversion
    :param dec_num: decimal number
    :param value: binary converted
    :return: value
    """
    print(dec_num)
    if dec_num == 0:
        return value
    if dec_num % 2 == 0:
        value = '0' + value
    else:
        value = '1' + value
    return dec_to_bin(dec_num//2, value)


x = int(input('Enter a number: '))
print(dec_to_bin(x, ""))
