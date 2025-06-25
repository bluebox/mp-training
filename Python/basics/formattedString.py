name="saneeth"
age=15
print(f"my name is {name}")
val="my name is {} and i am {} years old".format(name,age)
print(val)

nums=[1,2,3,4,5,6]
print(sum(nums))
print(max(nums))

print(enumerate(nums))

mp={"anand":1}
print(mp.get("anand",0))
mp["abhi"]=2
print(sorted(mp))
print(sorted(mp.values()))