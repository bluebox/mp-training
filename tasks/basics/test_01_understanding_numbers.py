"""MODULE TO ILLUSTRATE FUNCTIONALITY OF ASSERT"""

__author__ = 'Hari'

from tasks.placeholders import *


# For most of these tests use the interpreter to fill up the blanks.
# type(object) -> returns the object's type.

def test_numbers_types():
    """test numbers types"""
    assert True is isinstance(1, int)
    assert True is isinstance(1.3, float)
# assert 'int' == type(10L).__name__
# In Python 3 the long datatype has been removed and all integer values are handled
# by the Int class. The default size of Int will depend on your CPU architecture.
# 32-bit systems the default datatype for integers will be
# 'Int32'->[-2147483648,2147483647]
# 64-bit systems the default datatype for integers will be
# 'Int64'->[-9223372036854775808,9223372036854775807]


def test_numbers_int_arithmetic_operations():
    """test numbers int arithmetic operations"""
    assert 30 == 10 + 20
    assert 200 == 10 * 20
    assert 32 == 2 ** 5
    assert -10 == 10 - 20
    assert 2.3333333333333335 == 7 / 3


def test_numbers_string_to_int():
    """hint: execute  print int.__doc__ in python console
       to find out what int(..) does"""
    assert 255 == int("FF", 16)
    assert 63 == int("77", 8)


def test_numbers_int_to_string():
    """test numbers int to string"""
    assert '0o12' == oct(10)
    assert '0x64' == hex(100)
    assert '0b11111111' == bin(255)


def test_numbers_long():
    """Long is not the long in c"""
    assert 1606938044258990275541962092341162602522202993782792835301376 == 2 ** 200


THREE_THINGS_I_LEARNT = """
-we have a method in python to convert a decimal number to octal
- a method to convert decimal to hexadecimal
- a method to convert decimal to binary
- hexadecimal, octal and binary function returns value as a string
"""

TIME_TAKEN_MINUTES = 4
