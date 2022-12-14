import csv
file = open('TestCSV.csv', 'r')
reader = csv.reader(file)
new_list = []
roll = int(input("enter the roll of student to be updated"))
found = False
for row in reader:
    if row[0] == str(roll):
        found = True
        stream = input("Enter the new Stream ")
        row[2] = stream
    new_list.append(row)
file.close()

if not found:
    print("Student not found")
else:
    file = open('TestCSV.csv', 'w+', newline='')
    writer = csv.writer(file)
    writer.writerows(new_list)
    file.seek(0)
    reader = csv.reader(file)
    for row in reader:
        print(row)
    file.close()
