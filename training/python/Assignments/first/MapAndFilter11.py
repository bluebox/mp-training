def process_names(names):
    return list(filter(lambda name:len(name)>3,map(str.capitalize,names)))

names=["ada","grace","charles","tim","alan"]
print(process_names(names))