def covert_to_custom_base(decimal_num,base):
    string=""
    hex={10:'A',11:'B',12:'C',13:'D',14:'E',15:'F'}

    while(decimal_num/base!=0):
        if decimal_num%base>9:
            string+=hex[decimal_num%base]
        else:
            string+=str(decimal_num%base)
        decimal_num=decimal_num//base
    return string[::-1]
    

print(covert_to_custom_base(255,16))
print(covert_to_custom_base(100,2))
print(covert_to_custom_base(42,8))
