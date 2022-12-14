import csv

rows = []

with open('employee.csv', 'r') as file:
    reader = csv.reader(file)
    header = reader.__next__()
    for row in reader:
        rows.append(row)

print(header)
print(rows)
