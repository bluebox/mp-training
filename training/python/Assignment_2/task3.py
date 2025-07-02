
def get_status_message(value, is_active, limit):
    # status = "Adult" if age >= 18 else "Minor"
    if not isinstance(value, int) or not isinstance(is_active, bool) or not isinstance(limit, int):
        return "Invalid input type"
    status = "High Alert" if is_active and value > limit\
        else "Moderate" if is_active and value <= limit else \
        "Inactive" if not is_active and value < 0 else "IDLE"
    return status

print(get_status_message("", True, 5))   
print(get_status_message(3, True, 5))   
print(get_status_message(-1, False, 5)) 
print(get_status_message(0, False, 5)) 