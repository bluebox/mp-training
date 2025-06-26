def invert_dictionary(dictionary):
    result = {}
    for i in dictionary:
        if not dictionary.get(i) in result:
            result[dictionary.get(i)] = []
        result[dictionary.get(i)].append(i)

    return result

d = {'a': 1, 'b': 2, 'c': 1, 'd': 0}
returned_result = invert_dictionary(d)
print(returned_result)
