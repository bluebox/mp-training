"""Module to convert binary to decimal"""


def bin_to_decimal(bin_str, n, value):
    """
    function to convert from binary to decimal
    :param bin_str: binary string
    :param n: len of binary string
    :param value: decimal value
    :return: value --> decimal
    """
    if n == 0:
        return value
    if bin_str[n - 1] == '1':
        value += 2**(len(bin_str) - n)
    return bin_to_decimal(bin_str, n - 1, value)


bin_string = input('Enter a binary string: ')
print(bin_to_decimal(bin_string, len(bin_string), 0))
