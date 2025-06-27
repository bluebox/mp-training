class student:
    def __init__(self,name,age,fee):
        self.name=name
        self._age=age
        self.__fee=fee
    def get_fee(self):
        return self.__fee
    def __get_student_details(self):
        print("details")
    def get_details(self):
        return self.__get_student_details()

abhi=student("abhi",22,8000)
# print(abhi.__fee) cannot access it because it is a private variable
# we can it by _student__fee that is _classname__attribute
# print(abhi._student__fee)
# print(abhi._age) not recommended
print(abhi.get_fee())
print(abhi.name)
# print(abhi.__get_student_details) cannot use this
print(abhi.get_details())
