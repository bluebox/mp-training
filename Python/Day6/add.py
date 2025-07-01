def add(a,b):
    try:
        return a+b
    except TypeError as e:
        raise TypeError("type error")