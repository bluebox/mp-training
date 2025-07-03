def create_or_update_item(items_dict, key, value=None):
    modify_dic = items_dict.copy()
    if value:
        modify_dic[key] = value
    else:
        if key in modify_dic:
            modify_dic.pop(key)
    return modify_dic
dic = { "a" : 1,"b" :2 ,"c" : 3}

create = create_or_update_item(dic,"d",5)
print(create)

update = create_or_update_item(dic,"a",10)
print(update)

read = create_or_update_item(dic,"e")
print(read)

delete = create_or_update_item(dic,"b")
print(delete)