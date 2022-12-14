"""Module named Meetings, has a functionality of creating, reading , updating csv files
   of Data consisting 1. Employee , in time, out-time , break time,  2. employee, meetings. Algorithm
   to fetch available meeting time of requested time interval is written. If Meeting available their
   respective record are updated."""

import csv

import pandas as pd


def create1():
    """creates csv file1 containing info about employee working hours"""
    header = ['Employee', 'in-time', 'out-time', 'break-time']
    data = [['Abhishek Gupta', '10', '19', '14-15'],
            ['Abhishek MV', '09', '18', '13-14'],
            ['Nishit Kumar', '11', '20', '14-15'],
            ['Fardeen khan', '08', '17', '12-13'],
            ['Bipin Oraon', '12', '21', '15-16'],
            ['Nikhil Samudrala', '10', '19', '15-16']]

    data = pd.DataFrame(data, columns=header)
    data.to_csv('employee_working_hours.csv', index=False)


def create2():
    """creates csv file2 containing info about employee meetings"""
    data = pd.read_csv('employee_working_hours.csv')
    col1 = data["Employee"].tolist()
    col2 = ['11-12', '10-11', '12-13', '11-12', '16-17', '17-18']
    fields = ['Employee', 'Meeting']

    lst = []
    for i in range(len(col1)):
        dict1 = {fields[0]: col1[i], fields[1]: col2[i]}
        lst.append(dict1)

    # name of csv file
    filename = "employee_meetings.csv"

    # writing to csv file
    with open(filename, 'w') as csvfile:
        # creating a csv dict writer object
        writer = csv.DictWriter(csvfile, fieldnames=fields)

        # writing headers (field names)
        writer.writeheader()

        # writing data rows
        writer.writerows(lst)


def create():
    """creates a csv file consisting of info about available time slots"""
    data = pd.read_csv('employee_working_hours.csv')
    data1 = pd.read_csv('employee_meetings.csv')
    col1 = data["Employee"].tolist()

    fields = ['Employee', 'Available slots']

    in_time = data['in-time'].tolist()
    out_time = data['out-time'].tolist()
    break_time = data['break-time'].tolist()
    meeting_time = data1['Meeting'].tolist()
    col2 = []
    for i in range(len(in_time)):
        lst = []
        break_start = break_time[i][0] + break_time[i][1]
        break_end = break_time[i][-2] + break_time[i][-1]
        meet_start = meeting_time[i][0] + meeting_time[i][1]
        meet_end = meeting_time[i][-2] + meeting_time[i][-1]
        if int(in_time[i]) != int(meet_start):
            s1 = str(in_time[i]) + '-'
            if int(break_start) < int(meet_start):
                if int(meet_start) == int(break_end):
                    s1 += break_start
                    lst.append(s1)
                    s2 = meet_end + '-' + str(out_time[i])
                    lst.append(s2)
                else:
                    s1 += break_start
                    lst.append(s1)
                    s2 = break_end + '-' + meet_start
                    lst.append(s2)
                    s3 = meet_end + '-' + str(out_time[i])
                    lst.append(s3)
            else:
                if int(meet_end) == int(break_start):
                    s1 += meet_start
                    lst.append(s1)
                    s2 = break_end + '-' + str(out_time[i])
                    lst.append(s2)
                else:
                    s1 += meet_start
                    lst.append(s1)
                    s2 = meet_end + '-' + break_start
                    lst.append(s2)
                    s3 = break_end + '-' + str(out_time[i])
                    lst.append(s3)
        else:
            if int(meet_end) == int(break_start):
                s1 = break_end + '-' + str(out_time[i])
                lst.append(s1)
            else:
                s1 = meet_end + '-'
                s1 += break_start
                lst.append(s1)
                s2 = break_end + '-' + str(out_time[i])
                lst.append(s2)
        col2.append(lst)

    # name of csv file
    filename = "employee_free_slots.csv"

    diction = []
    for i in range(len(col1)):
        dict1 = {fields[0]: col1[i], fields[1]: col2[i]}
        diction.append(dict1)

    # writing to csv file
    with open(filename, 'w') as csvfile:
        # creating a csv dict writer object
        writer = csv.DictWriter(csvfile, fieldnames=fields)

        # writing headers (field names)
        writer.writeheader()

        # writing data rows
        writer.writerows(diction)


def read():
    """reads the selected csv file"""
    data = pd.read_csv(input("Enter the csv file name to display: "))
    print(data)


def update():
    """updates the selected csv file"""
    file = input("Enter the name of the csv file to be updated: ")
    df = pd.read_csv(file)
    row = int(input("Enter row no. to be updated: "))
    col = input("Enter the column name : ")
    data = input("Enter the new data: ")
    # updating the column value/data
    df.loc[row, col] = data

    # writing into the file
    df.to_csv(file, index=False)
    create()
    s = input("Available time slots of employees has also been updated. Do you want to see the changes? "
              "(y/n): ")
    if s == 'y':
        data = pd.read_csv('employee_free_slots.csv')
        print(data)


def append():
    """adds new rows to the csv files"""
    file = input("Enter name of the file to be appended: ")
    if file == "employee_working_hours.csv":
        header = ['Employee', 'in-time', 'out-time', 'break time']
        data = []
        print("Enter data for the new row: ")
        for i in range(4):
            data.append(input())
    else:
        header = ['Employee', 'Meetings']
        data = []
        print("Enter data for the new row: ")
        for i in range(2):
            data.append(input())
    data = [list(data)]
    data = pd.DataFrame(data, columns=header)
    data.to_csv(file, mode='a', index=False, header=False)


def do(stuff):
    if stuff == 1:
        update()
    elif stuff == 2:
        append()
    elif stuff == 3:
        read()
    elif stuff == 4:
        return
    s1 = int(input(f"Choose from the given options: \n 1. Update \n 2. Append \n 3. Read \n 4. Exit \n"))
    do(s1)


stuff = int(input(f"Choose from the given options: \n 1. Update \n 2. Append \n 3. Read \n 4. Exit \n"))
do(stuff)

