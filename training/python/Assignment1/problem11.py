def capitalized_name(name):
    return name.capitalize()

def filtered_names(name):
    return not len(name) < 4

def process_names(names_list):
    capitalizednames = map(capitalized_name, names_list)
    filtered_result = filter(filtered_names, list(capitalizednames))
    return list(filtered_result)

names = ["ada", "grace", "charles", "tim", "alan","   "]
print(process_names(names))