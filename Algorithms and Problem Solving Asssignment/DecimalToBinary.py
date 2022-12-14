def decimal_to_binary(decimal_input):
    decimal_input = int(decimal_input)
    binary_output = ''
    while decimal_input != 0:
        binary_output = str(decimal_input % 2) + str(binary_output)
        decimal_input //= 2
    print(binary_output)


decimal_to_binary(input('enter a decimal number to get the binary number of it : '))
