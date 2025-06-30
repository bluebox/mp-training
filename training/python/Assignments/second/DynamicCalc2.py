def dynamic_calculator(operation,*numbers,**options):
    if operation=="add" or operation=="subtract":
        res=options.get("initial_value",0)
    elif operation=="multiply" or operation=="division":
        res=options.get("initial_value",1)
    else:
        return "invalid operation"
    safe_division=options.get("safe_division",True)
    round_result=options.get("round_result",False)
    for i in numbers:
        if operation=="add":
            res+=i
        elif operation=="subtract":
            res-=i
        elif operation=="multiply":
            res*=i
        elif operation=="division":
            if safe_division and i==0:
                return "Error: Division by zero"
            res/=i
    if round_result:
        return round(res,2)
    return res
    


print(dynamic_calculator("Multiply",5,2,0,initial_value=0,round_result=True,safe_division=False))
