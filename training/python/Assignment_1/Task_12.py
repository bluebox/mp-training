#   Custom Base Converter :
#
#
# Problem Statement: Write a function convert_to_custom_base(decimal_num, base)
# that converts a positive integer from decimal (base 10) to a string representation of that number in a different base.
#
# Requirements:
# The function should support any base from 2 (binary) up to 16 (hexadecimal).
# For bases greater than 10, the digits 10 through 15 should be represented by the uppercase letters 'A' through 'F'.
# You cannot use built-in functions like bin(), oct(), or hex().
# You must implement the conversion logic yourself (typically using division and tracking remainders).

# Input: decimal_num = 255, base = 16
# Expected Output: 'FF'
# Input: decimal_num = 100, base = 2
# Expected Output: '1100100'
# Input: decimal_num = 42, base = 8
# Expected Output: '52'


def hexa_converter(decimal_num):
    sum = ''
    # digits='0123456789ABCDEF'
    digits = { 0 : '0', 1 : '1', 2 : '2', 3 : '3', 4 : '4', 5 : '5', 6 : '6', 7 : '7', 8 : '8',
               9 : '9', 10: 'A', 11: 'B', 12: 'C', 13: 'D', 14: 'E', 15: 'F'
            }
    while (decimal_num > 0):
        remainder = decimal_num % 16
        if remainder not in digits :
            print("Error in system")
            return
        sum += digits[remainder]
        decimal_num //= 16
    return sum

def bin_converter(decimal_num):
    sum=0
    itr=1
    while(decimal_num>0):
        remainder=decimal_num % 2
        sum+=itr*remainder
        decimal_num//=2
        itr*=10
    return sum
def octa_converter(decimal_num):
    sum = 0
    itr = 1
    while (decimal_num > 0):
        remainder = decimal_num % 8
        sum += itr * remainder
        decimal_num //= 8
        itr *= 10
    return sum

def convert_to_custom_base(decimal_num, base) :
    if base == 16 :
        print(f"Decimal to Hexa conversion of {decimal_num} is {hexa_converter(decimal_num)}")
        # print(hexa_converter(decimal_num))
    elif base == 2:
        print("Decimal to binary converter ",bin_converter(decimal_num))
    elif(base==8):
        print("Decimal to octal converter ",octa_converter(decimal_num))
    else:
        print("can't represent")

input = {
    255 : 16 ,
    100 : 2 ,
    42 : 8
}
for key,value in input.items():
    decimal_num = key
    base = value
    convert_to_custom_base(decimal_num, base)
