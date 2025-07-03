def create_counter(initial_count=0):
    count = initial_count
    def counter_func():
        nonlocal count
        def reset_counter():
            nonlocal count
            count = initial_count
            print("count ",count)
        counter_func.reset = reset_counter
        count += 1
        return count
    return counter_func

counter = create_counter(5)
print(counter()) 
print(counter()) 
counter.reset()
print(counter()) 