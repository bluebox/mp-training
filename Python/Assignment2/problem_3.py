



def get_status_message(value, is_active, limit):
    return "High Alert" if is_active and value > limit else "Moderate" if is_active and value <= limit else "inactive" if not is_active and value < 0 else "idle"


value = int(input("Enter value: "))
is_active = input("Enter True for active, False for inactive: ")
is_active = is_active == 'True'
limit = int(input("Enter limit: "))

print(get_status_message(value, is_active, limit))
