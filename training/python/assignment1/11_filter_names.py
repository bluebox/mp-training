# def fun(s):
#     if len(s) > 3:
#         return True
#     return False
# def cap(s):
#     return s.capitalize()

def process_names(names):
    # names = list(map(cap,names))
    # print(names)
    return list(filter(lambda x: len(x) > 3,map(lambda s: s.capitalize(),names)))

name = ["ada", "grace", "charles", "tim", "alan"]
print(process_names(name))