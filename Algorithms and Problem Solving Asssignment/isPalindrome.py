def is_palindrome_or_not(palindrome):
    rear_index, front_index = len(palindrome) - 1, 0
    while front_index <= rear_index:
        if palindrome[front_index] != palindrome[rear_index]:
            return 'No it is not a palindrome'
        front_index += 1
        rear_index -= 1
    return 'It is a palindrome'


input_string = input('Enter the string to check whether it is palindrome or not : ')
print(is_palindrome_or_not(input_string))
