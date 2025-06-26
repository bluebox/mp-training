def map_num_to_chars(s):
    char_map_dict = {
        0:"0",
        1:"1",
        2:"2",
        3:"3",
        4:"4",
        5:"5",
        6:"6",
        7:"7",
        8:"8",
        9:"9",
        10:"A",
        11:"B",
        12:"C",
        13:"D",
        14:"E",
        15:"F"
    }
    if s in char_map_dict:
        return char_map_dict[s]
    return None


def convert_to_custom_base(decimal_num,base):
    conv_num = ""
    while decimal_num>0:
        rem = decimal_num%base
        conv_num = map_num_to_chars(rem)+conv_num
        decimal_num = decimal_num//base
    return conv_num
print(convert_to_custom_base(100,2))
print(convert_to_custom_base(255,16))
print(convert_to_custom_base(42,8))
print(convert_to_custom_base(48,7))