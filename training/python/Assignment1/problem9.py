def is_palindrome_slicing(data):
    if len(data) < 2:
        return True
    reversed_data = data[::-1]
    for i in range(len(data)):
        if not reversed_data[i] == data[i]:
            return False

    return True

li = []
li2 = [1, 2, 3]
str1 = "madam"
str2 = "madamm"
print(is_palindrome_slicing(li))
print(is_palindrome_slicing(li2))
print(is_palindrome_slicing(str1))
print(is_palindrome_slicing(str2))