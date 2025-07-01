def dynamic_calculator(operation,*numbers,**options):
    initial_value = options.get('initial_value', 0)
    round_result = options.get('round_result', False)
    safe_division = options.get('safe_division', True)
    if operation.lower() == 'add':
        for i in numbers:
            initial_value+=i
    elif operation.lower() == 'subtract':
        for i in numbers:
            initial_value-=i
    elif operation.lower() == 'multiply':
        for i in numbers:
            initial_value*=i
    else:
        if safe_division:
            if 0 in numbers:
                return "Error: Division by Zero"
            else:
                for i in numbers:
                    initial_value/=i
        else:
            for i in numbers:
                initial_value/=i
    if round_result:
        initial_value = round(initial_value,2)
    return initial_value
print(dynamic_calculator("divide",initial_value=10,safe_division = True))


