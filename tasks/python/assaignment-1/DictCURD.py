def create_or_update_item(items_dict:dict,key,value=None):
    if value is None:
        if key in items_dict:
            items_dict.pop(key)
    else:
       items_dict[key] = value
    return items_dict

d = {"a":1,"b":2,"c":3,"d":4}
# print(create_or_update_item(d,"a",4))
# print(create_or_update_item(d,"c"))
# print(create_or_update_item(d,"e",5))