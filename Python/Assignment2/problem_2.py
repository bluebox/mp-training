def dynamic_calculator(operation,*numbers,**options):
    if operation not in ['add','sub','multiply','division']:
        print("invalid operation")
        return
    round_result=options.get('round_result',False)
    safe_division=options.get('safe_division',True)
    if operation in ['add','sub']:
        result=options.get('inital_value',0)
    else:
        result=options.get('initial_value',1)
    for num in numbers:
        if operation=='add':
            result+=num
        elif operation=='sub':
            result-=num
        elif operation=='multiply':
            result*=num
        else:
            if safe_division:
                if num==0:
                    return "Error: Division by zero"
                result/=num
            else:
                result/=num
    if round_result:
        result=round(result,2)
    return result


a=dynamic_calculator('division',1,2,3,0)
print(a)