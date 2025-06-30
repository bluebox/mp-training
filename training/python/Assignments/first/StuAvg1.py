def get_student_info():
    stuList=[]
    for i in range(2):
        id=input("enter id: ")
        name=input("enter name: ")
        scores=tuple(map(int,input("enter 3 scores: ").split(" ")))
        if len(scores)!=3:                                              #condition to check whether user entered 3 scores or not
            print("enter exactly 3 scores if no score enter 0 for it")   
            continue
        stuList.append((id,name,scores))
    return stuList
stuList=get_student_info()
for s in stuList:
    id,name,scores=s
    s1,s2,s3=scores
    print(f"Name : {name}\nAverage Score : {(s1+s2+s3)/3}")
        
  











