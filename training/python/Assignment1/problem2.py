def group_by_key(tuples_list):
    result = {}
    for key, value in tuples_list:
        if key not in result:
            result[key] = []
        result[key].append(value)
    return result

li = [
    ("name", "surya"),
    ("age", 21),
    ("address", "Hyderabad"),
    ("address", "Warangal"),
    ("name", "Sai"),
    ("age", 25)
]

returned_result = group_by_key(li)
print(returned_result)