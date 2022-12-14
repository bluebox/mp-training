from datetime import datetime

a = datetime(1999, 11, 12, 12, 12, 12)

print("year =", a.year)
print("month =", a.month)
print("hour =", a.hour)
print("minute =", a.minute)
print("timestamp =", a.timestamp())

today = datetime.now()

# Accessing Attributes
print("Day: ", type(today.day))
print("Month: ", today.month)
print("Year: ", today.year)
print("Hour: ", today.hour)
print("Minute: ", today.minute)
print("Second: ", today.second)
print(today)
