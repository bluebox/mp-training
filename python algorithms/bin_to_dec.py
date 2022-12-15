'''This Module is for binary to Decimal Conversion.'''


def to_dec(bin_str, ind_cnt, val):
    '''FUNCTION TO CONVERT BINARY TO DECIMAL.'''
    if ind_cnt == 0:
        return val
    if bin_str[ind_cnt - 1] == '1':
        val += 2**(len(bin_str) - ind_cnt)
    return to_dec(bin_str, ind_cnt - 1, val)


binary = input('Enter a binary string: ')
print(to_dec(binary, len(binary), 0))
