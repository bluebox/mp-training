def create_counter(initial_count=0):
    count = initial_count

    def counter_func():
        nonlocal count
        count += 1

        def reset_counter():
            nonlocal count
            count = initial_count
            return reset_counter

        counter_func.reset = reset_counter
        return count

    return counter_func


b = create_counter(9)
print(b())
print(b())
b.reset()
print(b())

a = create_counter(100)
print(a())
print(b())
