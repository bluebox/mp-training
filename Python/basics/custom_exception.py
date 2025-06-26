class age_exception(Exception):
    def __init__(self,msg="enter a valid age"):
        super().__init__(msg)
try:
    age = int(input("enter age: "))
    if(age<18 or age>100):
        raise age_exception
    print(age)
except age_exception as e:
    print(e)
