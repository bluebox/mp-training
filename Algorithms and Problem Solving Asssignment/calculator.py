def calculator(current_value):
    operation = input('*, +, -, /  pick an operation : ')
    if operation == '*':
        current_value *= int(input(f'{current_value} {operation} '))
    elif operation == '+':
        current_value += int(input(f'{current_value} {operation} '))
    elif operation == '-':
        current_value -= int(input(f'{current_value} {operation} '))
    elif operation == '/':
        current_value //= int(input(f'{current_value} {operation} '))
    else:
        return None, current_value
    print(current_value)
    return current_value, None


current_value_1 = int(input('enter the first number : '))
while True:
    current_value_1, current_value_2 = calculator(current_value_1)
    if None == current_value_1:
        print(f'your current value is {current_value_2}')
        break
