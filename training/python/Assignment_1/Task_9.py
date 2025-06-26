#
#  Palindrome Check with Slicing :
#
# Problem Statement: Write a function is_palindrome_slicing(data) that determines
# if a given sequence (a list or a string) is a palindrome. A palindrome is a sequence that reads the same forwards and backward.
# Requirements:
# The function must perform the check using a single comparison operation involving list/string slicing.
# It should not use loops, the .reverse() method, or the reversed() function.
# The function should work for both lists (e.g., [1, 2, 1]) and strings (e.g., "madam")

def is_palindrome_slicing(data):
   data_reverse = data[::-1]
   if data == data_reverse:
       print("list has palindromic sequence")
   else:
       print("not a palindrome")
data=[1,2,2,1]
# data=[3,2,1]

is_palindrome_slicing(data)