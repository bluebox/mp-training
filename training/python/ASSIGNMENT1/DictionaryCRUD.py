def create_or_update_item(items_dict, key, value=None):
    if value==None or value== "None":
        try:
            del items_dict[key]
        except:
            pass
    else:
        items_dict[key]=value
    return items_dict

dict1 = {'a': 10, 'b': 20, 'c': 30}
print(create_or_update_item(dict1,"f"))
print(create_or_update_item(dict1,"b",1000))
print(create_or_update_item(dict1,"c","None"))