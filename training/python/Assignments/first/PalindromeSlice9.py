def is_palindrome_slicing(data):
    if data==data[::-1]:
        return "Palindrome"
    else:
        return "Not a palindrome"
list1=[1,2,3,2,1]
print("List is",is_palindrome_slicing(list1))
string1="madam"
print("String is",is_palindrome_slicing(string1))