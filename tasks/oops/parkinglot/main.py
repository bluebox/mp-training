from math import floor
# import bill

# from lot import *


# run entry file
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
NOTE = '''kjdsfnnnnnnnnnnnnnnnndj'''
def func(time_spent):
    # print('frgr')
    if time_spent < 2:
        print("pay:20rs")
        return
    elif 1 < time_spent < 11:
        pay=20
        time=time_spent-1
        pay=pay+time*10
        print("pay {0}".format(pay))
        return
    elif time_spent > 10:
        pay=110
        time=time_spent-10
        pay=pay+time*5
        print("pay {0}".format(pay))
        return
def bill(typ, num):
    flag = 0
    # for i in range(4):
    #     for j in range(10):
    #         if lot[i][j] != 0.0:
    #             if lot[i][j].v_number == num:
    #                 flag = 1
    #                 if j < 6:
    #                     vehicle[0] += lot[i][j].v_width * lot[i][j].v_depth
    #                 if 5 < j < 7:
    #                     vehicle[1] += lot[i][j].v_width * lot[i][j].v_depth
    #                 if 6 < j < 10:
    #                     vehicle[2] += lot[i][j].v_width * lot[i][j].v_depth
    #                 print("enter time")
    #                 exit_time = float(input())
    #                 entry_time = lot[i][j].v_intime
    #                 lot[i][j] = 0.0
    #                 time_spent = exit_time - entry_time
    #                 pemp = floor(time_spent)
    #                 if time_spent - pemp < 0.30:
    #                     time_spent = pemp
    #                 else:
    #                     time_spent = pemp + 1

    count = -1
    if typ == 1:
        for item in A:
            count += 1
            if item.v_number == num:
                flag = 1
                print("enter time")
                exit_time = float(input())
                entry_time = item.v_intime
                vehicle[0] += item.v_width * item.v_depth
                del A[count]
                time_spent = exit_time - entry_time
                pemp = floor(time_spent)
                if time_spent - pemp < 0.30:
                    time_spent = pemp
                else:
                    time_spent = pemp + 1
                print('parking space for cars is', vehicle[0])
                # print("tetetetetete")
                func(time_spent)
    if typ == 2:
        for item in B:
            if item.v_number == num:
                flag = 1
                print("enter time")
                exit_time = float(input())
                entry_time = item.v_intime

                vehicle[1] += item.v_width * item.v_depth
                A.pop(item)
                time_spent = exit_time - entry_time
                pemp = floor(time_spent)
                if time_spent - pemp < 0.30:
                    time_spent = pemp
                else:
                    time_spent = pemp + 1
                print('parking space for bus is', vehicle[1])
                func(time_spent)
    if typ == 2:
        for item in A:
            if item.v_number == num:
                flag = 1
                print("enter time")
                exit_time = float(input())
                entry_time = item.v_intime

                vehicle[2] += item.v_width * item.v_depth
                A.pop(item)
                time_spent = exit_time - entry_time
                pemp = floor(time_spent)
                if time_spent - pemp < 0.31:
                    time_spent = pemp
                else:
                    time_spent = pemp + 1
                print('parking space for bikes is', vehicle[2])
                func(time_spent)

    if flag == 0:
        print('enter correct number')
        num = input()
        bill(num)

# import park
def park(obj, tem):
    if tem == 1:
        if vehicle[0] > obj.v_width * obj.v_depth:
            # for i in range(4):
            #     for j in range(6):
            #
            #         if lot[i][j] == 0.0:
            #             lot[i][j] = obj
            #             vehicle[0] -= obj.v_width * obj.v_depth
            #             return
            #         if lot[3][5] != 0.0:
            vehicle[0] -= obj.v_width * obj.v_depth
            A.append(obj)
            print('car is parked successfully \nremaining area for car is', vehicle[0])
        else:
            print('parking lot is full')

    if tem == 2:
        if vehicle[1] > obj.v_width * obj.v_depth:
            # for i in range(4):
            #     for j in range(6, 7):
            #         if lot[i][j] == 0.0:
            #             lot[i][j] = obj
            #             vehicle[1] -= obj.v_width * obj.v_depth
            #             return
            #         if lot[3][6] != 0.0:
            #             print('lot is full')


            vehicle[1] -= obj.v_width * obj.v_depth
            B.append(obj)
            print('bus is parked successfully \nremaining area for bus is', vehicle[1])
        else:
            print('parking lot is full')
    if tem == 3:
        if vehicle[2] > obj.v_width * obj.v_depth:
            # for i in range(4):
            #     for j in range(7, 10):
            #         if lot[i][j] == 0.0:
            #             lot[i][j] = obj
            #             vehicle[2] -= obj.v_width * obj.v_depth
            #             return
            #         if lot[4][9] != 0.0:
            #             print('lot is full')
            vehicle[0] -= obj.v_width * obj.v_depth
            C.append(obj)
            print('bike is parked successfully \nremaining area for bikes is', vehicle[2])
        else:
            print('parking lot is full')
# import vehicle
class Vehicle:
    def __init__(self, v_milage, v_name, v_capacity, v_width, v_depth, v_number, v_manufacturer, time):
        self.v_milage = v_milage
        self.v_name = v_name
        self.v_capacity = v_capacity
        self.v_width = v_width
        self.v_depth = v_depth
        self.v_number = v_number
        self.v_manufacturer = v_manufacturer
        self.v_intime = time
# import lot
# run entry file
vehicle = [6000, 1000, 3000]
A = []
B = []
C = []
D = []

# lot = [A, B, C, D]
# run this file
while True:
    print('enter a for parking')
    print('enter b for withdraw')
    print('enter c for display')
    kemp = input()
    if kemp == 'a':
        print('Enter type of vehicle ')
        print('enter 1 for Car \nenter 2 for Bus \nenter 3 for Bike')
        tem = int(input())
        if tem == 1:
            print('Enter v_milage, v_name, v_capacity, v_width, v_depth, v_number, v_manufacturer', 'time')
            v_milage = int(input())
            v_name = input()
            v_capacity = int(input())
            v_width = int(input())
            v_depth = int(input())
            v_number = input()
            v_manufacturer = input()
            time = float(input())
            Car = Vehicle(v_milage, v_name, v_capacity, v_width, v_depth, v_number, v_manufacturer, time)
            park(Car, tem)
        elif tem == 2:
            print('Enter v_milage, v_name, v_capacity, v_width, v_depth, v_number, v_manufacturer')
            v_milage = int(input())
            v_name = input()
            v_capacity = int(input())
            v_width = int(input())
            v_depth = int(input())
            v_number = input()
            v_manufacturer = input()
            time = float(input())
            Bus = Vehicle(v_milage, v_name, v_capacity, v_width, v_depth, v_number, v_manufacturer, time)
            park(Bus, tem)
        elif tem == 3:
            print('Enter v_milage, v_name, v_capacity, v_width, v_depth, v_number, v_manufacturer', 'time')
            v_milage = int(input())
            v_name = input()
            v_capacity = int(input())
            v_width = int(input())
            v_depth = int(input())
            v_number = input()
            v_manufacturer = input()
            time = float(input())
            Bike = Vehicle(v_milage, v_name, v_capacity, v_width, v_depth, v_number, v_manufacturer, time)
            park(Bike, tem)
        else :
            print('enter 1 for Car \n enter 2 for Bus \n enter 3 for Bike')

    if kemp == 'b':
        print('enter 1 for Car \nenter 2 for Bus \nenter 3 for Bike')
        typ =int(input())
        print('enter vehicle number')
        num = input()
        bill(typ, num)
    if kemp == 'c':
        count = 0
        for i in A:
            print(i.v_number)
            count += 1
        for i in B:
            print(i.v_number)
            count += 1
        for i in C:
            print(i.v_number)
            count += 1
        print("There are {} vehicles".format(count))
    print("do u want to continue y/n")
    var = input()
    if var == 'n':
        break
# test comment