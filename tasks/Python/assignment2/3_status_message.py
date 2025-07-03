def get_status_message(value, is_active, limit):
    return "High Alert" if (is_active and value > limit) else ("Moderate" if (is_active and value <= limit) else ("Inactive" if value < 0 else "Idle"))

print(get_status_message(25,False,20))
print(get_status_message(-25,False,20))
print(get_status_message(25,True,30))
print(get_status_message(25,True,20))

