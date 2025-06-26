#  Flatten a Nested List :
#
# Problem Statement: Write a function flatten_list(nested_list) that takes a list which contains
# other lists as its elements (a 2D list) and "flattens" it into a single, one-dimensional list.
#
# Requirements:
# The solution must be implemented using a nested list comprehension.
# Do not use any external libraries or complex recursive solutions (assume it's only one level deep).

def flatten_list(nested_list):
    check=set()
    flat_list = [ x for each in nested_list  for x in (each if (isinstance(each, list) )  else [each]) if not (x in check or check.add(x)) ]
    print("flattened list")
    print(flat_list)

nested_list=[    1,'kk',
                ['a' , 'b' , 'c'],
                ['a' , 'b' , 'd'],
                ['a' , 'b' , 'c'],
                ['a' , 'b' , 'c']
            ]
flatten_list(nested_list)

