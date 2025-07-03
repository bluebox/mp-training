import json
def is_strong(pwd):
    if pwd is None:
        pass#none value exception raise
    elif not isinstance(pwd, str):
        pass#not a string exception raise
    else:
        return True
def convert_to_json(homeclass,file_name):
    json_data = homeclass.__dict__
    json_object = json.dumps(json_data, indent=4,default=lambda o:o.__dict__)
    with open(file_name, "w") as file:
        file.write(json_object)

def load_class(file_name):
    with open(file_name, "r") as file:
        json_data = json.load(file)
    return json_data