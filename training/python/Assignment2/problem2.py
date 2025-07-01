def dynamic_calculator(operation, *numbers, **options):
    if operation in ["add", "subtract"]:
        result = options.get("initial_value", 0)
    elif operation in ["multiply", "divide"]:
        result = options.get("initial_value", 1)
    else:
        return "Error: Unsupported operation"

    round_result = options.get("round_result", False)
    safe_division = options.get("safe_division", True)

    try:
        for num in numbers:
            if operation == "add":
                result += num
            elif operation == "subtract":
                result -= num
            elif operation == "multiply":
                result *= num
            elif operation == "divide":
                if num == 0:
                    if safe_division:
                        return "Error: Division by zero"
                    else:
                        result /= num
                else:
                    result /= num
                    
    except ZeroDivisionError:
        return "Error: Division by zero in except"

    if round_result:
        result = round(result, 2)

    return result

print(dynamic_calculator("add", 1, 2, 3))  
print(dynamic_calculator("multiply", 2, 3, 4, initial_value=1))  
print(dynamic_calculator("subtract", 10, 2, 1, round_result=True))  
print(dynamic_calculator("divide", 100, 5, 2, initial_value=1000, safe_division=True, round_result = True))
print(dynamic_calculator("divide", 100, 5, safe_division=True))
print(dynamic_calculator("divide", 100, 0, safe_division=False))  
