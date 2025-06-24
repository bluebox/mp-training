string = "String"
tup = (1,2,3,4,5,6)

mem_view_1 = memoryview(bytearray(string,'utf-8'))
print(mem_view_1)
mem_view_2 = memoryview(bytearray(tup))
print(mem_view_2)
mem_view_1[0] = ord('s')
print(mem_view_1.tobytes().decode())