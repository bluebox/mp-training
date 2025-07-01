from random import randint
def solve(a,b):
   try:
       b=int(b)
       if 1<b<11:
           if a==b:
               return True
       else:
           print("enter a no between 1-10")
           return False
   except ValueError as err:
       return err
def main():
    guess=randint(1,10)
    while True:
        answer=int(input("enter your answer: "))
        if solve(guess,answer):
            print("you are a genius")
            break

if __name__=='__main__':
    main()
    # what ever the code that doesn't to run outside the file write that in __name__=='__main__'
    #block because when this executes in another module it run and the __name__ as the module not __main__
    # but when we run this file its gives __name__ as __main__ so this works
