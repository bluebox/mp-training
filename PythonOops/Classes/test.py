from datetime import datetime


def calculate_charge(in_time: datetime, out_time: datetime):
    diff = out_time - in_time
    total_minutes = diff.total_seconds()//60
    print(total_minutes)
    hours = total_minutes // 60
    minutes = total_minutes % 60
    if minutes > 30:
        hours += 1
    if hours > 10:
        return 5 * hours
    elif hours > 1:
        return 10 * hours
    else:
        return 20

print(calculate_charge(datetime(2022, 10, 12, 18, 10, 3), datetime(2022, 10, 12, 23, 40, 3)))