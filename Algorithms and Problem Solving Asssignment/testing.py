from datetime import datetime


def creating_time_stamp(present_time):
    a = datetime(present_time.year, present_time.month, present_time.day, present_time.hour, present_time.minute, present_time.second)
    return a.timestamp()


timestamp1 = 0
timestamp2 = 0
while True:
    inp = input("enter 1 or 2 or 3")
    if inp == '1':
        timestamp1 = creating_time_stamp(datetime.now())
        print(timestamp1)
    elif inp == '2':
        timestamp2 = creating_time_stamp(datetime.now())
        print(timestamp2)
    else:
        print(abs(timestamp2 - timestamp1))
        break


