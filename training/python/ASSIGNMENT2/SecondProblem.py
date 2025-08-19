def dynamic_calculator(operation, *numbers, **options):
    initial = {"add": 0, "subtract": 0, "multiply": 1, "divide": 1}

    default_options = {"initial_value": initial[operation],
                       "round_result": False, "safe_division": True}
    options = {**default_options, **options}
    result = 0

    if operation in ['add', 'subtract']:
        result = 0
    elif operation in ['divide', 'multiply']:
        result = 1
    if options["initial_value"]:
        result = options["initial_value"]

    if operation == "add":
        for i in numbers:
            result += i
    elif operation == "subtract":
        for i in numbers:
            result -= i
    elif operation == "multiply":
        for i in numbers:
            result *= i
    elif operation == "divide":
        for i in numbers:
            if i == 0:
                if options["safe_division"]:
                    return "Error: Division by zero"
                else:
                    raise ZeroDivisionError
            else:
                result /= i

    else:
        return "improper operation.please try again!!"

    if options["round_result"]:
        result = round(result, 2)

    return result


print(dynamic_calculator("add", 1, 2, 3, 4, 5, 6))
print(dynamic_calculator("subtract", 1, 2, 3, 4, 5, 6))
print(dynamic_calculator("multiply", 1, 2, 3, 4, 5, 6))
print(dynamic_calculator("divide", 1, 2, 3, 4, 5, 6))
print(dynamic_calculator("divide", 1, 2, 3, 4, 0, 6, safe_division=True))