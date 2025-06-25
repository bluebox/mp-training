def convert(val, a):
    s = ''
    while val >= a:
        s = str(val % a) + s
        val //= a
    if val:
        s = str(val) + s
    return s


def hexVal(rem):
    if rem == 15:
        return 'F'
    elif rem == 14:
        return 'E'
    elif rem == 13:
        return 'D'
    elif rem == 12:
        return 'c'
    elif rem == 11:
        return 'B'
    elif rem == 10:
        return 'A'
    return str(rem)


def convert_to_custom_base(val, base):
    if base == 2:
        return convert(val, 2)
    elif base == 8:
        return convert(val, 8)
    else:
        s = ''
        while val >= 16:
            rem = val % 16
            val //= 16
            s = hexVal(rem) + s
        if val:
            s = hexVal(val) + s
        return s


val = int(input("enter value to convert: "))
base = int(input("enter base: "))

print(convert_to_custom_base(val, base))

#--------------- for verification

if base==2:
    print(bin(val))
elif base==8:
    print(oct(val))
else :
    print(hex(val))