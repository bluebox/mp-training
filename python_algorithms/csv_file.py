import csv

field_names = ['Name', 'Roll_no', 'Branch', 'CGPA']
row = [['Abhishek Gupta', 10, 'Civil', 9.3],
       ['Abhishek Veeraghantimath', 11, 'Mechanical', 8.0],
       ['Bipin Oraon', 19, 'IT', 8.2],
       ['Nikhil', 51, 'Mechanical', 8.6],
       ['Nishit', 54, 'CS', 8.7]]

file_name = 'student_records.csv'
with open(file_name, 'w') as csvfile:
    csvwriter = csv.writer(csvfile)
    csvwriter.writerow(field_names)
    csvwriter.writerows(row)

with open(file_name, mode='r') as file:
    csv_reader = csv.DictReader(file)
    for lines in csv_reader:
        print(lines)

