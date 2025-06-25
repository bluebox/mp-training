def is_palindrome_slicing(ans):
    val=ans[::-1]
    return ans==val

print(is_palindrome_slicing('madam'))
print(is_palindrome_slicing([1,2,1]))