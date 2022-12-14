import csv


def reading_employee_file():
    with open('employee_timings.csv', 'r') as csv_file:
        csv_reader = csv.reader(csv_file)
        fields = next(csv_reader)
        print(', '.join(field for field in fields))
        for details in csv_reader:
            print(f'{details[0]} {details[1]} {details[2]} {details[3]} {details[4]}')


def reading_emp_meetings():
    with open('employee_meetings.csv', 'r') as file_obj:
        reader_obj = csv.reader(file_obj, quotechar='"', delimiter=',', quoting=csv.QUOTE_ALL)
        fields = next(reader_obj)
        print(', '.join(field for field in fields))
        for details in reader_obj:
            print(f'{details[0]} {details[1]} {details[2]}')


def appending_new_employee():
    emp_id = input("Enter the new Employee's id : ")
    emp_name = input('Enter the new employee name : ')
    emp_in_time = input('Enter the new employee in time : ')
    emp_out_time = input('Enter the new employee out time : ')
    emp_break_time = input('Enter the employee Break time : ')
    emp_details = [emp_id, emp_name, emp_in_time, emp_out_time, emp_break_time]
    with open('employee_timings.csv', 'a') as e_file:
        writer_object = csv.writer(e_file)
        writer_object.writerow(emp_details)
        e_file.close()
    print('The New employee has been appended into the records')


def string_to_digit(meeting_time):
    start_time, end_time = meeting_time.split('-')
    uncombined_time_list = [start_time, end_time]
    combined_list = []
    for time in uncombined_time_list:
        digit_time = ''
        for digit in time:
            if digit != ':':
                digit_time += digit
        combined_list.append(int(digit_time))
    return combined_list


def error_message_2():
    print('Sorry! you are overlapping currently scheduled meetings')


def error_message_1():
    print("Sorry! you are scheduling meeting during the break time")


def schedule_meetings():
    emp_id = input("Enter the employee ID : ")
    emp_name = input("Enter the employee Name to schedule meeting with : ")
    meeting_time_to_schedule = input("Enter the meeting time in format HH:MM-HH:MM : ")
    start_time, end_time = string_to_digit(meeting_time_to_schedule)
    all_emp_meetings_list = []
    found = False

    with open('employee_meetings.csv', 'r') as emp_meetings:
        reader = csv.reader(emp_meetings, quotechar='"', delimiter=',', quoting=csv.QUOTE_ALL)
        all_emp_meetings_list.append(next(reader))
        for emp in reader:
            if emp[0] == emp_id:
                found = True
                emp_meetings = emp[2]
                emp_meetings_list = emp_meetings.split(',')
                for emp_meeting in emp_meetings_list:
                    emp_meeting_start_time, emp_meeting_end_time = string_to_digit(emp_meeting)
                    if 1300 <= start_time < 1400:
                        error_message_1()
                        return
                    if 1300 < end_time < 1400:
                        error_message_1()
                        return
                    if emp_meeting_start_time < start_time < emp_meeting_end_time:
                        error_message_2()
                        return
                    if emp_meeting_start_time < end_time < emp_meeting_end_time:
                        error_message_2()
                        return
                updated_emp_meeting = emp_meetings + ',' + meeting_time_to_schedule
                updated_emp = [emp[0], emp[1], updated_emp_meeting]
                all_emp_meetings_list.append(updated_emp)
                continue
            all_emp_meetings_list.append(emp)
        if not found:
            append_new_employee = [emp_id, emp_name, meeting_time_to_schedule]
            all_emp_meetings_list.append(append_new_employee)

    with open('employee_meetings.csv', 'w') as file:
        writer = csv.writer(file)
        writer.writerows(all_emp_meetings_list)

    print(f'Your meeting is scheduled with {emp_name} during {meeting_time_to_schedule} \n '
          f'The updated employee Meeting List is : ')


def main():
    while True:
        option_chosen = input("Enter the option for required service \n1. For scheduling a meeting with an employee \n"
                              "2. For Viewing the list of employees with meetings scheduled \n"
                              "3. For viewing the list of employees and their in and expected out time \n"
                              "4. For appending a new employee entry in to the employee list \n"
                              "5. For exit\n")

        if option_chosen == '1':
            schedule_meetings()
        elif option_chosen == '2':
            reading_emp_meetings()
        elif option_chosen == '3':
            reading_employee_file()
        elif option_chosen == '4':
            appending_new_employee()
        else:
            return


if __name__ == '__main__':
    main()
