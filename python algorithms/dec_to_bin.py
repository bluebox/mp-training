"""
Decimal to Binary Convertor.
"""


def to_bin(num, binary):
    """
    FUNCTION TO CONVERT DECIMAL TO BINARY
    :param num: decimal number.
    :param binary: binary value to be returned.
    :return: binary
    """
    if num == 0:
        return binary

    return to_bin(num//2, binary) + str(num % 2)


x = int(input('Enter a number: '))
print(to_bin(x, ""))
