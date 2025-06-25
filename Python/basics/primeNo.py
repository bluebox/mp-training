def solve(n)->bool:
    for i in range(2,n):
        if n%i==0:
            return False
    return True
n=int(input("enter value to check prime: "))
if solve(n):
    print(n,"is a prime no")
else:
    print(n,"is not a prime no")