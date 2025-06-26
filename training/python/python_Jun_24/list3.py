# TTask 3:
# Reverse a list using slicing and without using .reverse().

print("Task 3: Reverse a list using slicing and without using .reverse() and list copy")
basket = [ "cookies" , "chocolates" , "chips" , "fruits" , "vegetables"]

basket2=basket[:]
basket3=basket
basket[0]="biscuits"
print(basket)
print(basket2)
print(basket3)
print("reverssed")
print(basket[::-1])