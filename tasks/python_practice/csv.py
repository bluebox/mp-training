import csv
import pandas as pd

def create():
    col =['Empolyee_Name','In_Time','Out_Time','Break_Time']
    rows = [['Guptaji','9:30','6:30','2:00'],
            ['MV','9:00','6:30','2:00'],
            ['Deen_Dayalu','10:00','7:00','2:00']]

    with open('EMP_Time.csv','w',encoding='UTF8',newline="") as f:
        writer = csv.writer(f)
        writer.writerow(col)
        writer.writerows(rows)

def read():
    with open('EMP_Time.csv', mode='r') as f:
        reader = csv.reader(f)

        for lines in reader:
            print(lines)

def read1():
    df = pd.read_csv(r'/home/medplus/EMP/venv/EMP_Time.csv')
    print(df)

create()
read()
