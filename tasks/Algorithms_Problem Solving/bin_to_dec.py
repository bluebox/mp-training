"""
Converts binary number to decimal number
"""


def bin_to_decimal(bnum):
    """Function to convert binary number to its decimal equivalent"""
    i = 1
    dec = 0
    # bstr = (str(bnum))[::-1]
    # for k in bstr:
    #     dec += int(k)*i
    #     i = i*2
    while bnum != 0:
        rem = bnum % 10
        dec += rem*i
        i = i*2
        bnum = bnum // 10
    print(f'Your decimal equivalent to binary number is = {dec}\n')


def short_bin_to_dec(bnum):
    """Short-Cut Function to convert binary number to its decimal equivalent"""
    dec = int(str(bnum), 2)
    print(f'Your decimal equivalent to binary number is = {dec}\n')


number = int(input('Enter a binary number\n'))
bin_to_decimal(number)
short_bin_to_dec(number)
