import csv
import pandas as pd


def create_table(file_name):
    field = ['emp_num', 'emp_name', 'in_time', 'out_time', 'break']

    with open(file_name, 'w') as csv_file:
        csv_writer = csv.writer(csv_file)
        csv_writer.writerow(field)


def create_emp_meetings(emp_detail_file, emp_meeting_file):
    fields = ['emp_no', 'employee', 'meeting-time']
    file = open(emp_detail_file, 'r')
    reader = csv.reader(file)
    emp_meeting_list = []
    for row in reader:
        emp_meeting_list.append([row[0], row[1], [row[2], row[4], row[3]]])
    print(emp_meeting_list)
    file.close()
    file = open(emp_meeting_file, 'w')
    writer = csv.writer(file)
    writer.writerow(fields)
    writer.writerows(emp_meeting_list)
    file.close()


def update_table(file_name):
    roll = input('Enter the roll number of the student:')
    value_to_be_updated = input('Updated value: ')
    field_number = int(input('Enter field number: '))
    op = open(file_name, 'r')
    reader = csv.reader(op)
    updated_row = []
    for row in reader:
        if roll == row[0]:
            row[field_number] = value_to_be_updated
        updated_row.append(row)
    op.close()
    file = open(file_name, 'w+')
    writer = csv.writer(file)
    writer.writerows(updated_row)
    file.seek(0)
    reader = csv.reader(file)
    for row in reader:
        print(row)
    file.close()


def add_employee(file_name):
    x = int(input('How many employees you want to add: '))
    row = []
    for i in range(x):
        lis = []
        emp_num = int(input('Enter employee number: '))
        emp_name = input('Enter name: ')
        in_time = int(input('Enter in time: '))
        out_time = int(input("Enter out time "))
        break_time = int(input('Enter break time: '))
        lis.append(emp_num)
        lis.append(emp_name)
        lis.append(in_time)
        lis.append(out_time)
        lis.append(break_time)
        row.append(lis)

    with open(file_name, 'a') as csv_file:
        csv_writer = csv.writer(csv_file)
        csv_writer.writerows(row)


def read_employee_table(file_name):
    with open(file_name, 'r') as csv_file:
        csv_reader = csv.reader(csv_file)
        for line in csv_reader:
            print(line)
    df = pd.read_csv(file_name)
    print(df)


def check_availability(emp_number, file_name, time):
    with open(file_name, 'r') as file:
        reader = csv.reader(file)
        for row in reader:
            if emp_number == row[0]:
                print(True)
                if int(row[2][0]) <= time < int(row[2][1]) or int(row[2][1]) + 1 <= time < int(row[2][2]):
                    return True
                else:
                    return False

        return False


emp_details_file = "emp_detail.csv"
emp_meetings_file = "emp_meetings.csv"
# create_table(emp_details_file)
# read_employee_table(emp_details_file)
# add_employee(emp_details_file)
read_employee_table(emp_details_file)
create_emp_meetings(emp_details_file, emp_meetings_file)
if check_availability('120', emp_meetings_file, 11):
   print('Employee available')
else:
    print('Employee not available')
