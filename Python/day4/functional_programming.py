# a pure function is a function which doesn't manipulates the outside data

def multiply_by(li):
    dp=[]
    for item in li:
        dp.append(item*2)
    return dp

li=[1,2,3,4]
dp=multiply_by(li)
print(li)
print(dp)

# here the li is not manipulated the data
# this is a pure function
# function program is used for better understanding,code reuasbility and etc

