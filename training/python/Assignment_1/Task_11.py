# map and filter for Name Processing :
#
# Problem Statement: Write a function process_names(names) that takes a list of strings (names).
# The function must first capitalize the first letter of every name,
# then filter out any names that are shorter than 4 characters long.
#
# Requirements:
# You must use the map() function to perform the capitalization (e.g., with str.capitalize).
# You must use the filter() function to remove the short names.
# The final result should be returned as a list.
# Input: names = ["ada", "grace", "charles", "tim", "alan"]
# After map: ["Ada", "Grace", "Charles", "Tim", "Alan"]
# After filter (length > 3): ["Grace", "Charles", "Alan"]
# Expected Output: ['Grace', 'Charles', 'Alan']

def process_names(names):
    print("After map")
    capitalise=list(map(str.capitalize,names))
    print(capitalise)
    big_names= list(filter(lambda item: len(item) > 3, capitalise))
    print(big_names)

names = ["ada", "grace", "charles", "tim", "alan"]
process_names(names)
