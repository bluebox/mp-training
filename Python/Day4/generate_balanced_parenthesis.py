n=int(input())
def solve(a,b,s):
   if len(s)>=2*n:
       a=''.join(s)
       print(a)
       return
   if a<3:
       s.append('(')
       solve(a+1,b,s)
       s.pop()
   if b<a:
       s.append(')')
       solve(a,b+1,s)
       s.pop()
solve(0,0,[])