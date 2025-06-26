def process_names(names):
    long_names = list(filter(lambda s:any(True if vow_char in s.lower()  else False for vow_char in "aeiou"), map(str.capitalize,names)))
    return long_names

names_list = ["Madhav","ram","NLV","Rahul"]
print(process_names(names_list))






