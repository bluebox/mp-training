def format_currency(amount,symbol='$'):
    amount=round(amount,2)
    amount=format(amount,',')
    return f'{symbol}{amount}'
amount=1376890769775.09090
amount=format_currency(amount)
print(amount)