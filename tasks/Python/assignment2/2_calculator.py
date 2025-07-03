from functools import reduce

def dynamic_calculator(operation, *numbers, **options):
    initial_value = options.setdefault("initial_value", None)
    round_result = options.setdefault("round_result", False)
    safe_division = options.setdefault("safe_division", True)

    if initial_value is None:
        if operation in ["add", "subtract"]:
            initial_value = 0
        elif operation in ["multiply", "divide"]:
            initial_value = 1

    result = initial_value

    if operation == "add":
        result = reduce(lambda x,y:x+y, numbers, initial_value)
    elif operation == "subtract":
        result = reduce(lambda x,y:x-y, numbers ,initial_value)
    elif operation == "multiply":
        result = reduce(lambda x,y:x*y, numbers, initial_value)
    elif operation == "divide":
        try:
            result = reduce(lambda x,y:x/y, numbers, initial_value)
        except ZeroDivisionError:
            if safe_division:
                return "Error division by zero"
            raise ZeroDivisionError


    # for num in numbers:
    #     if operation == "add":
    #         result += num
    #     elif operation == "subtract":
    #         result -= num
    #     elif operation == "multiply":
    #         result *= num
    #     elif operation == "divide":
    #         if safe_division and num == 0:
    #             return "Error division by zero"
    #         result /= num

    if round_result:
        return round(result, 2)
    return result

print(dynamic_calculator('add', 1, 2, 3))
print(dynamic_calculator('subtract', 10, 3, 2))
print(dynamic_calculator('multiply', 2, 3, 4))
print(dynamic_calculator('divide', 10, 2))

print(dynamic_calculator('add', 1, 2, 3, initial_value=5))
print(dynamic_calculator('multiply', 2, 3, initial_value=2))


print(dynamic_calculator('divide', 10, 3, round_result=True))

print(dynamic_calculator('divide', 10, 0, safe_division=True))
# print(dynamic_calculator('divide', 10, 0, safe_division=False))
