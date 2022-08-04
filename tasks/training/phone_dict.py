#method  (1)

data = {}

flag = True
while flag:
    try:
        input1 = int(input("choose one option \n(1) Add \n(2) Search \n(3) Delete  \n(4) search by partial name \n(5) close \n "))
        if input1 == 1:
            name = input("enter the name : ")
            place = input("enter the address : ")
            phone_number = str(input("enter the number: "))
            while(len(phone_number)>10):
                 print("You entered wrong number")
                 phone_number = str(input("enter the number: "))
            if input("any alternate number choose \n yes (or) no \n") == "yes":
                alternate_mobile_no = input("enter the alternate mobile number")
                data[name] = [name, phone_number, alternate_mobile_no, place]
            else:
                data[name] = [name, phone_number, place]
        elif input1 == 2:
            searching_by_name = input("enter the name : ")
            if searching_by_name not in data.keys():
                print("No data found by the provided details...")
            else:
                print("The following data is found by provided details")
                print(data[searching_by_name])
        elif input1 == 3:
            name = input("enter the name of the person to delete contact details: ")
            del data[name]
        elif input1==4:
            check=True
            searching_partial_name=input("enter the partial name ")
            for name in data.keys():
                if data[name][0].startswith(searching_partial_name):
                    print("data found by provided partial name")
                    print(data[name])
                    check=False
            if check:
                print("no data found by the provided information")

        elif input1 == 5:
            flag = False

        else:
            print("enter the valid number from 1 to 4")

    except ValueError:
        print("enter the integer values between 1 to 4")

    print()

