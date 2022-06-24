from math import floor
from lot import *


def bill(num):
    flag = 0
    for i in range(4):
        for j in range(10):
            if lot[i][j] != 0.0:
                if lot[i][j].v_number == num:
                    flag = 1
                    if j < 6:
                        vehicle[0] += lot[i][j].v_width * lot[i][j].v_depth
                    if 5 < j < 7:
                        vehicle[1] += lot[i][j].v_width * lot[i][j].v_depth
                    if 6 < j < 10:
                        vehicle[2] += lot[i][j].v_width * lot[i][j].v_depth
                    print("enter time")
                    exit_time = float(input())
                    entry_time = lot[i][j].v_intime
                    lot[i][j] = 0.0
                    time_spent = exit_time - entry_time
                    pemp = floor(time_spent)
                    if time_spent - pemp < 0.30:
                        time_spent = pemp
                    else:
                        time_spent = pemp + 1
                    if time_spent < 2:
                        print("pay:20rs")
                        return
                    elif 1 < time_spent < 11:
                        print("pay {0}".format(time_spent * 10))
                        return
                    elif time_spent > 10:
                        print("pay {0}".format(time_spent * 5))
                        return

    if flag == 0:
        print('enter correct number')
        num = input()
        bill(num)
