import math
print("Enter the exponent :: ")
power = int(input())
for i in range(power+1):
    coeff = math.comb(power,i)
    coeff1 = math.factorial(power) / (math.factorial(i) * math.factorial(power - i))
    if(i != power):
        print(f"{coeff} x^{i} +",end = " ")
    else:
        print(f"{coeff1} x^{i}")#format(coeff=math.factorial(power) / (math.factorial(i) * math.factorial(power - i)),i = i))
