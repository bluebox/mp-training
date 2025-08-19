def process_names(names):
    After_map=map(lambda i:i.capitalize(),names)
    After_filter=filter(lambda i:len(i)>3,After_map)
    return list(After_filter)

names = ["ada", "grace", "charles", "tim", "alan"]