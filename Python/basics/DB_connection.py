import mysql.connector

try:
    db = mysql.connector.connect(
        host="localhost",
        user="Anand",
        password="1925112816@Aa",
        database="test"
    )
    cursor = db.cursor()

    cursor.execute("SELECT * FROM Employee")

    for col in cursor.description:
        print(col[0], end=" ")
    print()

    rows = cursor.fetchall()
    for row in rows:
        print(row)

    cursor.execute("SELECT * FROM Employee")
    row = cursor.fetchone()
    print("First row:", row)
    print(cursor.statement)
except Exception as e:
    print(e)
