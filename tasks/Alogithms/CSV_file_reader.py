# Program to read a csv file and print it

import csv

with open('sample2.csv', 'r') as csv_file:
    csv_reader = csv.reader(csv_file)
    for line in csv_reader:
        print(line)