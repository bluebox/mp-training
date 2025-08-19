def create_counter(initial_count=0):
    original_count=initial_count
    def counter_func():
        nonlocal initial_count
        def reset_counter():
            nonlocal initial_count
            initial_count=original_count
            return initial_count
        initial_count+=1
        counter_func.reset=reset_counter
        return initial_count

    return counter_func

a=create_counter(12)

print(a())
print(a())
print(a())
print(a())
print(a())
print(a.reset())
print(a())
print(a())
print(a())
print(a.reset())