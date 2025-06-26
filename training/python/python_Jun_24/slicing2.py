print("Task 2: Use slicing to copy a list. Modify the original and show that the copy didn’t change.")
basket = [ "cookies" , "chocolates" , "chips" , "fruits" , "vegetables"]

basket2=basket[:]
basket3=basket
basket[0]="biscuits"
print(basket)
print(basket2)
print(basket3)