def binary_to_decimal(binary_input):
    reversed_binary_input = binary_input[::-1]
    decimal_output, increment = 0, 0
    for binary_digit in reversed_binary_input:
        decimal_output += ((2 ** increment) * int(binary_digit))
        increment += 1
    print(decimal_output)
    return decimal_output


binary_to_decimal(input("Enter the binary form of a number : "))
