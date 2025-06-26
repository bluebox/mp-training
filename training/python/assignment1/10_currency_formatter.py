def format_currency(amount, currency_symbol='$'):
    amount = round(amount,2)
    return f"{currency_symbol} {amount:,}"

amount = 112123456712.8912134
print(format_currency(amount))