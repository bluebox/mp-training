def get_status_message(value,is_active, limit):
    return "Inactive" if not is_active and value<0 else "idle" if not is_active else "High Alert" if is_active and value>limit else "Moderate"

print(get_status_message(0,False,5))       