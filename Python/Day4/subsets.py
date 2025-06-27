ans=list(map(int,input().split()))
def solve(ind,st):
    if ind>=len(ans):
        print(st)
        return
    st.append(ans[ind])
    solve(ind+1,st)
    st.pop()
    solve(ind + 1, st)
solve(0,[])