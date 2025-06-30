def format_currency(amount,currency_symbol='$'):
    return f"{currency_symbol}{amount:,.2f}"

amount=1234567.896
print(format_currency(amount))