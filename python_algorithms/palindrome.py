def is_palindrome(string):
    i = 0
    j = len(string)-1
    while i < j:
        if string[i] != string[j]:
            return False
        i += 1
        j -= 1
    return True


s = input('Enter a string: ')
if is_palindrome(s):
    print(f'{s} is a palindrome.')
else:
    print(f'{s} is not a palindrome.')