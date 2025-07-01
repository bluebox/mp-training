import mysql.connector

def add_employee(age,name,salary):
    try:
        conn=mysql.connector.connect(
        host="localhost",
        user="Anand",
        password="1925112816@Aa",
        database="test"
    )
        cursor=conn.cursor()
        cursor.execute('insert into Employee(age,name,salary) values(%s,%s,%s)',(age,name,salary))
        conn.commit()
        cursor.close()
        conn.close()
        return True
    except Exception as e:
        raise e
def get_employee(id):
    try:
        conn=mysql.connector.connect(
            host="localhost",
            user="Anand",
            password="1925112816@Aa",
            database="test"
        )
        cursor=conn.cursor()
        cursor.execute(f'select * from Employee where id={id}')
        result=cursor.fetchall()
        return result
    except Exception as e:
        print(e)

get_employee(1)