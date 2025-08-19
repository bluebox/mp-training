

def format_currency(amount, currency_symbol='$') :
    amount=round(amount,2)
    amount = f"{amount:.2f}"
    # print(amount)
    i=amount.index(".")
    print(i)
    result=amount[:]
    for j in range(len(amount[:i])//3):
        i=i-3
        result=result[:i]+","+result[i:]
        print(result)
    return currency_symbol+result
amount = 12134567.896
print(format_currency(amount))
