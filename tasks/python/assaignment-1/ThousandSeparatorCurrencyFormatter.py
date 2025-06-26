def format_currency(amount,currency_symbol='$'):
    amount = float(amount)
    amount = round(amount,2)
    str_amt = str(amount)
    list_amt = list(str_amt)
    lhs = []
    rhs = []
    f = 1
    for i in list_amt:
        if i =='.':
            f = 0
            continue
        if f == 1:
            lhs.append(i)
        else:
            rhs.append(i)
    # print(lhs)
    # print(rhs)
    for i in range(len(lhs)-1,-1,-3):
        if i == len(lhs)-1:
            continue
        # print(i)
        lhs.insert(i+1,",")

    lhs_str = "".join(lhs)
    rhs_str = "".join(rhs)
    return f"{currency_symbol}{lhs_str}.{rhs_str}"
print(format_currency(123456789))




# str1 = "{} is {} years old".format("Madhav",21)
# print(str1)