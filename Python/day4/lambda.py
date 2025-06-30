def is_even(item):
    if item%2==0:
        return True
    return False
print(is_even(100))

iss=lambda x:x%2==0
print(iss(101))
# lambda can destroy then self and after use of it
del is_even
