def format_currency(amount, currency_symbol = '$'):
    rounded_amount = round(amount, 2)
    str_number = str(rounded_amount)
    arr = str_number.split(".")
    result = ''
    reversed_string = arr[0][::-1]
    looping_index = 0
    if len(reversed_string)%3 == 0:
        looping_index = len(reversed_string)//3
    else:
        looping_index = len(reversed_string)//3+1
    for i in range(looping_index):
        if not i == looping_index-1:
            result+=reversed_string[i*3:i*3+3]+','
        else:
            result += reversed_string[i*3:i*3+3]
    to_be_returned = f'{currency_symbol}{result[::-1]}.{arr[1]}'
    return to_be_returned

price = 12.865
print(format_currency(price))