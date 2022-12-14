import csv

import pandas as pd

def create():
    col =['Empolyee_Name','In_Time','Out_Time','Break_Time']
    rows = [['Guptaji','10','18','14-15'],
            ['MV','10','19','14-15'],
            ['Deen_Dayalu','10','19','14-15']]

    with open('EMP_Time.csv','w',encoding='UTF8',newline="") as f:
        writer = csv.writer(f)
        writer.writerow(col)
        writer.writerows(rows)

def create1():
    df = pd.read_csv(r'EMP_Time.csv') # Retrieving data into meeting file
    print(df)
    col = ['Empolyee_Name', 'Meetings']
    dt_fields = []
    meet = ['10-11', '15-16', '16-17']
    file = open("EMP_Time.csv",mode= 'r')
    j=0
    i=1
    reader = csv.reader(file)
    for row in reader:
        if i == 1:
            i += 1
            continue
        dt_fields.append([row[0],meet[j]])
        j+=1
    file.close()

    with open('EMP_Meet.csv','w',encoding='UTF8',newline="") as f:
        writer =csv.writer(f)
        writer.writerow(col)
        writer.writerows(dt_fields)
    nf = pd.read_csv(r'EMP_Meet.csv')
    print()
    print(nf)

def read():
    with open('EMP_Time.csv', mode='r') as f:
        reader = csv.reader(f)

        for lines in reader:
            print(lines)

def read1():
    df = pd.read_csv(r'EMP_Time.csv')
    print(df)

def update():
    df = pd.read_csv(r'EMP_Time.csv')
    field_name = input("Enter the field or col name under which updation are to be done ")
    emp_no = int(input("Enter the sl no of Emp to be updated: "))
    new_name = input("Enter the new name for Empolyee: ")
    print()
    df.loc[emp_no, field_name] = new_name
    df.to_csv("EMP_Time.csv", index=False)
    print(df)

def free_slots():
    file1 = open('EMP_Time.csv', mode = 'r')
    file2 = open('EMP_Meet.csv', mode = 'r')
    j=0
    details1 = []
    details2 = []
    reader1 = csv.reader(file1)
    for i in reader1:
        if j==0:
            j+=1
            continue
        details1.append([i[1],i[2],i[3]])
    j=0
    reader2 = csv.reader(file2)
    for i in reader2:
        k=i[1]
        if j==0:
            j+=1
            continue
        details2.append(k)


    j=0
    for i in details2:
        details1[j].append(i)
        j+=1

    j=0
    for i in details1:
        details1[j]=sorted(i)
        j+=1


    slots_persons = []

    for item in details1:
        slots = []
        j=0
        for i in item:
            if not (j < len(item)-1):
                break
            if len(i)==2:
                if (i == item[j+1][0:2]):
                    j+=1
                    pass
                else :
                    k=str(i + "-" +item[j+1][0:2])
                    slots.append(k)
                    j+=1
            else :
                if i[3:5] == item[j+1][0:2]:
                    j += 1
                    pass
                else:
                    k=str(i[3:5] + "-" +item[j+1][0:2])
                    slots.append(k)
                    j += 1
        slots_persons.append(slots)

    file1.close()
    file1 = open('EMP_Time.csv', mode='r')
    reader1 = csv.reader(file1)
    j=0
    emp_lst = []
    for i in reader1:
        if j==0:
            j+=1
            continue
        emp_lst.append(i[0])
    file1.close()
    file2.close()

    j=0
    slots_lst = []
    for i in slots_persons:
        slots_lst.append([emp_lst[j], i])
        j+=1


    col = ['Empolyee_Name', 'Available_slots']
    with open('EMP_slots.csv','w',encoding='UTF8',newline="") as f:
        writer =csv.writer(f)
        writer.writerow(col)
        writer.writerows(slots_lst)
    df = pd.read_csv(r'EMP_slots.csv')
    print()
    print(df)

create()
create1()
# read1()
update()

free_slots()