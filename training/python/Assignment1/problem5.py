def create_or_update_item(items_dict, key, value = None):
    if bool(value):
        if key not in items_dict:
            items_dict[key] = value
        else:
            items_dict.update({key: value})
    else:
        if key not in items_dict:
            pass
        else:
            items_dict.pop(key)

    return items_dict

d = {'a': 1, 'b': 2, 'c': 3}

print(create_or_update_item(d, 'e', 5))
print(create_or_update_item(d, 'b', 5))
print(create_or_update_item(d, 'e'))
print(create_or_update_item(d, 'f'))

