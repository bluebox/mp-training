#contact book
contacts = {}
while 1 :
    print("enter 1 to see contacts\n"
          "enter 2 to add contact\n"
          "enter 3 to serch contact\n"
          "enter 4 to exit")
    n = int(input())
    if n == 1:
        print(contacts)

    elif n == 2:
        name = input("enter name")
        ph_num = input("enter mobile number")
        if name in contacts:
            lis = [ph_num]
            lis.append(contacts[name])
            contacts[name]=lis
            # print(contacts[name])

        else:
            contacts[name] = ph_num

        print("contact added successfully")

    elif n == 3:
        k = input("enter name")
        if contacts[name]:
            print(contacts[name])
        else :
            print("contact not existed")

    elif n == 4:
        break

    else:
        print("enter a valid option")