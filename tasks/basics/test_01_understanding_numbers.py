__author__ = 'Hari'

from tasks.placeholders import *


# For most of these tests use the interpreter to fill up the blanks.
# type(object) -> returns the object's type.

def test_numbers_types():
    assert 'int' == type(1).__name__
    assert 'float' == type(1.3).__name__
# assert 'int' == type(10L).__name__
# In Python 3 the long datatype has been removed and all integer values are handled
# by the Int class. The default size of Int will depend on your CPU architecture.
# 32 bit systems the default datatype for integers will be 'Int32'->[-2147483648,2147483647]
# 64 bit systems the default datatype for integers will be 'Int64'->[-9223372036854775808,9223372036854775807]


def test_numbers_int_arithmetic_operations():
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
    assert '0o12' == oct(10)
    assert '0x64' == hex(100)
    assert '0b11111111' == bin(255)


def test_numbers_long():
    """Long is not the long in c"""
    assert 1606938044258990275541962092341162602522202993782792835301376 == 2 ** 200


three_things_i_learnt = """
-we have a method in python to convert a decimal number to octal
- a method to convert decimal tp hexadecemal
-
"""

time_taken_minutes = 4
