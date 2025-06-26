def format_currency(amount, symbol='$'):
    return f"{symbol}{format(round(amount, 2), ',')}"

amount = 1376890769775.09090
s = "${:,.2f}".format(amount)
print(s)
amount = format_currency(amount)
print(amount)
