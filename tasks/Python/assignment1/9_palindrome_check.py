def is_palindrome_slicing(data):
    return data == data[::-1]

str1 = "madam"
str2 = "Madam"
list1 = [1,2,1]
list2 = ["a","b","c"]

print(is_palindrome_slicing(str1))
print(is_palindrome_slicing(str2))
print(is_palindrome_slicing(list1))
print(is_palindrome_slicing(list2))