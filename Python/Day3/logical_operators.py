is_employee=True
is_manager=False

if is_employee and is_manager:
    print("manages")
elif  is_manager:
    print("belong to hr")
else:
    print("not a member of company")


is_student=False
is_completed_btech=True

if is_student or is_completed_btech:
    print("educated")
elif is_completed_btech:
    print("searching for job")
else :
    print("not educated")


# we generally use ! in C++ or in java for negation but in python we use not

temp=False

if not temp:
    print("in the block of if that uses not")
    # not True become True then enter the block