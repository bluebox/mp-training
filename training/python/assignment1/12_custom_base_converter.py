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

num = 500
base_value  = 16
print(convert_to_custom_base(num,base_value))