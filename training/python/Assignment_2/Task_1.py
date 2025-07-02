
# def create_counter(initial_count=0):
#     count = [initial_count]
#
#     def counter_func():
#         def reset_counter():
#             count[0] = initial_count
#         counter_func.reset = reset_counter
#         count[0] += 1
#         return count[0]
#
#     return counter_func

#using nonlocal
def create_counter(initial_count=0):
    count = initial_count

    def counter_func():
        def reset_counter():
            nonlocal count
            count = initial_count
        counter_func.reset = reset_counter
        nonlocal count
        count += 1
        return count

    return counter_func

c = create_counter(10)
print(c())
print(c())
c.reset()
print(c())
