def convert_to_custom_base(decimal_num, base):
    s=""
    while decimal_num != 0:
        rem = decimal_num % base
        if rem >= 10:
            digit = chr(55 + rem)
        else:
            digit = str(rem)
        s = digit + s
        decimal_num //=base
    return s

NUM = 500
BASE  = 16
print(convert_to_custom_base(NUM,BASE))
