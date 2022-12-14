def is_prime(number, flag):
    for num in range(2, number):
        if number % num == 0:
            flag = False
            break
    return flag


flag_input = True
number_to_be_checked = int(input('Enter the number to check whether it is prime or not : '))
if is_prime(number_to_be_checked, flag_input):
    print(f"The number {number_to_be_checked} is a prime")
else:
    print(f"The number {number_to_be_checked} is not a prime")

