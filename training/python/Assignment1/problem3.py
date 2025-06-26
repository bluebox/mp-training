def merge_and_sum(dictionary1, dictionary2):
    result = {}
    for i in dictionary1:
        result[i] = dictionary1.get(i)

    for i in dictionary2:
        if i in result:
            result[i] = result.get(i) + dictionary2.get(i)
        else:
            result[i] = dictionary2.get(i)
    return result

dict1 = {'a': 10, 'b': 20, 'c': 30}
dict2 = {'b': 5, 'c': 15, 'd': 40}

returned_result = merge_and_sum(dict1, dict2)
print(returned_result)

