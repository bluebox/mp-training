
def dynamic_calculator(operation, *numbers, **options):
    if operation not in ["add", "subtract", "multiply", "divide"]:
        return "Invalid operation"

    #initial values
    if operation in ["add", "subtract"]:
        initial_value = options.get("initial_value", 0)
    else:
        initial_value = options.get("initial_value", 1)

    round_result = options.get("round_result", False)
    safe_division = options.get("safe_division", True)

    try:
        if operation == "add":
            result = initial_value + sum(numbers)
        elif operation == "subtract":
            result = initial_value - sum(numbers)
        elif operation == "multiply":
            result = initial_value
            for n in numbers:
                result *= n
        elif operation == "divide":
            result = initial_value
            for n in numbers:
                if n == 0:
                    if safe_division:
                        return "Error: Division by zero"
                    else:
                        result /= n
                else:
                    result /= n

        if round_result:
            result = round(result, 2)

        return result

    except Exception as e:
        # print(e)
        return e
# print(dynamic_calculator("subtract", 1, 2, 3))
# print(dynamic_calculator("add", 5, 2, initial_value=10))
# print(dynamic_calculator("multiply", 2, 3, initial_value=4))
# print(dynamic_calculator("divide", 10, 2, safe_division=True))
# print(dynamic_calculator("divide", 10, 0))
# print(dynamic_calculator("divide", 10, 0, safe_division=False))
# print(dynamic_calculator("invalid", 1, 2))
print(dynamic_calculator("divide", 0, 0, initial_value=10, round_result=True, safe_division=False))