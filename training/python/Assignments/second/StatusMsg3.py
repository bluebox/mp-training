def get_status_msg(value,is_active,limit):
    str="High ALert" if is_active and value>limit else "Moderate" if is_active else "Inactive" if value<0 else "Idle"
    return str


# value=int(input("enter value"))
# is_active=bool(input("enter active status(True or False)"))
# limit=int(input("enter limit"))
# print(get_status_msg(value,is_active,limit))
print(get_status_msg(10,False,10))