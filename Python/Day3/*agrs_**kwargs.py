def solve(*args):
    print(*args) # print the elements
    print(args)  # prints the tuple of elements
    # *args store the element in tuple
solve(1,2,3,4,5,6,7,9)

def find(**kwargs):
    for key,val in kwargs.items():
        print(key,val)
    # **kwargs store the key arguments as a dict

find(name="anand",age=22,brother="saneeth")


# there is a specific order to use that
# value parameter ,*args,default parameter and **kwargs