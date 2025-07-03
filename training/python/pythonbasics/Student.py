marks = []
def getgrade(a):
    if a >= 90:
        return "A"
    elif a >= 80:
        return "B"
    elif a >= 70:
        return "C"
    elif a >= 50:
        return "D"
    else:
        return "F"

name = input("Enter Student Name: ")
i = 0
while i < 5:
    marks.append(int(input(f"Enter marks for subject {i+1}: ")))
    i+=1
print("\nname: ",name)
print(f"Total marks: {sum(marks)}/500")
print(f"Percentage: {sum(marks)/5}%")
print(f"Grade: {getgrade(sum(marks)/5)}")