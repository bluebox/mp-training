def check_mixed_input(data1, data2, data3):
    if data1:
        if not data2:
            if data3:
                return "Stage 1A: Data1 True, Data2 False, Data3 True"
            return "Stage 1B: Data1 True, Data2 False, Data3 False"
        return "Stage 1C: Data1 True, Data2 True"
    if not data3:
        if data2:
            return "Stage 2A: Data1 False, Data3 False, Data2 True"
        return "Stage 2B: Data1 False, Data3 False, Data2 False"
    return "Stage 2C: Data1 False, Data3 True"

print(check_mixed_input("hello", [], True))
print(check_mixed_input([1], None, False))
print(check_mixed_input(1, "world", []))
print(check_mixed_input(0, "active", 1))
print(check_mixed_input(None, "", 0))
print(check_mixed_input(False, [1, 2], None))
