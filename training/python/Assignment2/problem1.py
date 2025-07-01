def create_counter(initial_count=0):
    count = initial_count

    def counter_func():
        nonlocal count

        def reset_counter():
            nonlocal count
            count = initial_count

        counter_func.reset = reset_counter
        count += 1
        return count
    
    def initial_reset():
        return initial_count

    counter_func.reset = initial_reset
    return counter_func

counter = create_counter(10)

counter.reset()
print(counter())
print(counter())
counter.reset()
print(counter())

