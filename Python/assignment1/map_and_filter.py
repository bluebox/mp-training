def process_names(names):
    ans=list(map(lambda x:x.capitalize(),names))
    ans=list(filter(lambda x:len(x)>3,ans))
    return ans
names=["ada","grace",'charls','tim','alan']
names=process_names(names)
print(names)