import random
alphabets = "abcdefghijklmnopqrestuvbwxyz"
upper_case_alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
special_characters = "()*&^%$#[]{}/\!-+=:;~"
numbers = "1234567890"
generated_password = ""

for j in range(random.randint(8,25)):
    if j==0:
        generated_password+=random.choice(upper_case_alpha)
    elif j==1:
        generated_password+=random.choice(special_characters)
    elif j==2:
        generated_password+=random.choice(numbers)
    else:
        generated_password+=random.choice(alphabets)
print("the random generated password is ", generated_password)