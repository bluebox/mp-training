def create_or_update_item(items_dict,key,value=None):
    if value is not None:          #can use (if value:)
        items_dict[key]=value             #bcoz if it is None it automatically executes else only
    else:
        if key in items_dict:
            items_dict.pop(key)
    return items_dict


dict1={}
dict1['a'],dict1['b'],dict1['c']=10,20,30
create_or_update_item(dict1,'e',80)   
print(dict1)
create_or_update_item(dict1,'e', '')
print(dict1)