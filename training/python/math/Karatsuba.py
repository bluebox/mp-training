# Karatsuba algorithm is internally implemented in cpython for multiplying large number
# General multiplications are done through normal a*b way, which take about o(n^2) time complexity

def multiply(a,b):
    str_rep_a = str(a)
    no_of_bits_a = len(str_rep_a)
    str_rep_b = str(b)
    no_of_bits_b = len(str_rep_b)
    if no_of_bits_a<10 or no_of_bits_b<10:
        return a*b
    n = max(no_of_bits_a,no_of_bits_b)
    # str_rep_a.zfill(n)
    # str_rep_b.zfill(n)
    half_n = n//2
    higher_a = str_rep_a[:-half_n]
    print(higher_a)
    higher_b = str_rep_b[:-half_n]
    print(higher_b)
    lower_a = str_rep_a[-half_n:]
    lower_b = str_rep_b[-half_n:]
    a = multiply(int(higher_a),int(higher_b))
    c = multiply(int(lower_b),int(lower_a))
    e = multiply(int(higher_a)+int(lower_a),int(higher_b)+int(lower_b)) -a -c
    mult_number = a * (10**(2*half_n)) + e * (10 ** half_n) + c
    return mult_number

print(multiply(123456789012345678901234,123456789009876543211234567890))
