import lot
import vehicle
import park
import bill
while True:
    print('enter a for parking')
    print('enter b for withdraw')
    print('enter c for display')
    kemp = input()
    if kemp == 'a':
        print('Enter type of vehicle ')
        print('enter 1 for Car \n enter 2 for Bus \n enter 3 for Bike')
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
            Car = vehicle.Vehicle(v_milage, v_name, v_capacity, v_width, v_depth, v_number, v_manufacturer, time)
            park.park(Car, tem)
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
            Bus = vehicle.Vehicle(v_milage, v_name, v_capacity, v_width, v_depth, v_number, v_manufacturer, time)
            park.park(Bus, tem)
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
            Bike = vehicle.Vehicle(v_milage, v_name, v_capacity, v_width, v_depth, v_number, v_manufacturer, time)
            park.park(Bike, tem)
        else :
            print('enter 1 for Car \n enter 2 for Bus \n enter 3 for Bike')

    if kemp == 'b':
        print('enter vehicle number')
        num = input()
        bill.bill(num)
    if kemp == 'c':
        count = 0
        for i in range(4):
            for j in range(10):
                if lot.lot[i][j] != 0.0:
                    print(lot.lot[i][j].v_number)
                    count += 1
        print("There are {} vehicles".format(count))
    print("do u want to continue y/n")
    var = input()
    if var == 'n':
        break

