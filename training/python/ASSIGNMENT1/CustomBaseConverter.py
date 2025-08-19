def convert_to_custom_base(decimal_num, base) :
    base_list=[0,1,2,3,4,5,6,7,8,9,"A","B","C","D","E","F"]
    result=""
    while(decimal_num!=0):
        result=result+str(base_list[decimal_num%base])
        decimal_num=decimal_num//base
    return result[::-1]

print(convert_to_custom_base(125996,16))
print(convert_to_custom_base(255,8))
print(convert_to_custom_base(255,2))
print(convert_to_custom_base(255,4))


