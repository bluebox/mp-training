"""
Problem Statement:
 Implement a Python function create_counter(initial_count=0) that acts as a factory for counter functions.
create_counter should take an initial_count.
It should then return a nested function called counter_func.
counter_func should, when called, increment an internal count (initialized by initial_count) and return the new count.
Additionally, counter_func should have a nested function reset_counter() that resets the counter back to its initial_count (the one provided when create_counter was first called). This reset_counter function should also be accessible via an attribute of counter_func (e.g., counter_func.reset()).
Input: initial_count: An integer (optional, defaults to 0).
Output: A function (counter_func) that when called increments and returns the count, and also has a .reset() method.

"""

def create_counter(initial_count=0):
    count = [initial_count]

    def counter_func():
        def reset_counter():
            count[0] = initial_count
        counter_func.reset = reset_counter
        count[0] += 1
        return count[0]

    return counter_func

c = create_counter()
print(c())
print(c())
print(c())
print(c())

c.reset()
print(c())
