def convert_to_custom_base(decimal_num, base):
    temp = ''
    while decimal_num > 0:
        if decimal_num % base == 0:
            temp += '0'
        else:
            rem = decimal_num % base
            if base == 16:
                if rem > 9:
                    temp += chr(55 + rem)
            else:
                temp += str(rem)
        decimal_num = decimal_num // base
    return temp[::-1]

print(convert_to_custom_base(42, 8))
print(convert_to_custom_base(255, 16))
print(convert_to_custom_base(4, 2))

