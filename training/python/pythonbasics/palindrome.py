def is_palindrome(a):
    a=str(a)
    return a ==  a[::-1]

def base_k(n,k):
    c=""
    while n !=0:
        c = c+str(n%k)
        n//=k
    return c
def kmirror(k,n):
    c=0
    i=1
    s=0
    while c < n:
        if is_palindrome(i) and is_palindrome(base_k(i,k)):
            print(base_k(i,k))
            c+=1
            s+=i
        i+=1
    print(s)

k=3
n=7
kmirror(k,n)