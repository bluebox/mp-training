nth_fibo = int(input('what nth fibonacci number do you want ? '))

if nth_fibo > 2:
    prev_fibo = 0
    current_fibo = 1
    while nth_fibo != 2:
        next_fibo = current_fibo + prev_fibo
        prev_fibo = current_fibo
        current_fibo = next_fibo
        nth_fibo -= 1
    print(current_fibo)
elif nth_fibo == 2:
    print(1)
else:
    print(0)

