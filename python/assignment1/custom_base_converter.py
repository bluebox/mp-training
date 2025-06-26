""" this module converts a deciaml value to """
def convert_to_custom_base(val, base):
    """ this is the function """
    char_list = ['0', '1', '2', '3', '4', '5', '6',
                 '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F']
    s = ''
    while val >= base:
        rem = val % base
        s = char_list[rem] + s
        val //= base
    if val:
        s = char_list[val] + s
    return s


decimal_val = int(input("enter value to convert: "))
to_base = int(input("enter base: "))

print(convert_to_custom_base(decimal_val, to_base))
