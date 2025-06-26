def is_palindrome_slicing(data):
    if data == data[::-1]:
        return True
    return False
l = [[1,2,1],"madam","abcd"]
for i in l:
    if is_palindrome_slicing(i):
        print(f"{i} is a palindrome")
    else:
        print(f"{i} is not a palindrome")