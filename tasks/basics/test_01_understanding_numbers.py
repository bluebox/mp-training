'''number assert'''

__author__ = 'Hari'

#from tasks.basics.test_00_understanding_assert import TIME_TAKEN_MINUTES
from tasks.placeholders import __author__, __all__, __, ___

# For most of these tests use the interpreter to fill up the blanks.
# type(object) -> returns the object's type.

def test_numbers_types():
    '''types assertion'''
    assert 'int' == type(1).__name__
    assert 'float' == type(1.3).__name__
   # assert 'int' == type(10L).__name__

def test_numbers_int_arithmetic_operations():
    '''arithmetics assertion'''
    assert 30 == 10 + 20
    assert 200 == 10 *20
    assert 32 == 2 ** 5
    assert -10==10-20
    assert 2.3333333333333335 == 7/3

def test_numbers_string_to_int():
    '''string to int assertion'''

    assert 255 == int("FF", 16)
    assert 63== int("77", 8)

def test_numbers_int_to_string():
    '''int to string assertion'''
    assert '0o12' == oct(10)
    assert '0x64' == hex(100)
    assert '0b11111111' == bin(255)

def test_numbers_long():
    '''long assertion'''
    assert 1606938044258990275541962092341162602522202993782792835301376 == 2 ** 200


THREE_THINGS_I_LEARNT = """
-we have a method in python to convert a decimal number to octal
- a method to convert decimal tp hexadecemal
-
"""

TIME_TAKEN_MINUTES = 4
