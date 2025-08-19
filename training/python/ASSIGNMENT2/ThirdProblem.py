def get_status_message(value, is_active, limit):
    return "High Alert" if (is_active and value>limit)  else \
        ("Moderate" if (is_active and value<=limit) else
              ("Inactive" if (not is_active and value<0)
               else "Idle"))


print(get_status_message(70,True,50))
print(get_status_message(50,True,50))
print(get_status_message(20,True,50))
print(get_status_message(-22,False,50))
print(get_status_message(20,False,50))