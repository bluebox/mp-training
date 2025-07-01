new_file=open("test.txt",'w')
new_file.write("jai shree ram\n jai shree ram")
new_file.close()

new_file=open('test.txt')
print(new_file.readlines())
new_file.seek(0)
print(new_file.readline())
new_file.close()

new_file=open('test.txt','a')
new_file.write(" om namah shivayya")

new_file=open('test.txt')
print(new_file.readlines())
new_file.close()

# to automatically close the file we use to open file in with

with open('test.txt') as new_file:
    val=new_file.readlines()
    for i in val:
        print(i)

# this automatically close the file
'''
File Modes in Python:

1. 'r'   - Read (default mode)
          Opens the file for reading. File must exist.

2. 'w'   - Write
          Opens the file for writing. Creates if not exists. Overwrites if it exists.

3. 'a'   - Append
          Opens the file for appending. Creates if not exists. Data is written at the end.

4. 'x'   - Create
          Creates a new file. Raises error if the file exists.

5. 'r+'  - Read and Write
          File must exist. Allows reading and writing without truncating the file.

6. 'w+'  - Write and Read
          Creates the file if not exists. Truncates the file if it exists.

7. 'a+'  - Append and Read
          Creates the file if not exists. Reading starts from beginning, writing always at end.

8. 'x+'  - Create and Read/Write
          Creates the file. Raises error if it already exists.

Binary Modes:

9. 'rb'   - Read in binary mode
10. 'wb'  - Write in binary mode (truncate if exists)
11. 'ab'  - Append in binary mode
12. 'xb'  - Create in binary mode
13. 'rb+' - Read and write in binary mode
14. 'wb+' - Write and read in binary mode (truncate)
15. 'ab+' - Append and read in binary mode
16. 'xb+' - Create and read/write in binary modes
'''