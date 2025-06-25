file = None
try:
    file = open("test.txt", 'w')       # Overwrites the file
    file.write(" jai shree ram")       # Writes content
    file.flush()
    file.close()
    file = open("test.txt", 'r')
    content = file.read()
    print("File content after write:", content)
    # Forces buffer to disk
except Exception as e:
    print(e)
finally:
    if file:
        file.close()
