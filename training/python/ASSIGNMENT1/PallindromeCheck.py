def is_palindrome_slicing(data):
    return data==data[::-1]


list1=[1,2,3,5,3,2,1]

list2=[1,2,3,5,3,2,10]
str1="abcdcba"
str2="aabbccdccbaa"
print(is_palindrome_slicing(list1))
print(is_palindrome_slicing(list2))
print(is_palindrome_slicing(str1))
print(is_palindrome_slicing(str2))