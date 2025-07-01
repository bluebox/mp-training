def get_status_message(value, is_active, limit):
    return (
        "High Alert" if is_active and value > limit else
        "Moderate" if is_active and value <= limit else
        "Inactive" if not is_active and value < 0 else
        "Idle"
    )

print(get_status_message(120, True, 100))
print(get_status_message(80, True, 100)) 
print(get_status_message(-5, False, 100))
print(get_status_message(5, False, 100)) 