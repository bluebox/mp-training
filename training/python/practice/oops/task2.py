# Task 2: Class Methods and Static Methods
#
# Goal: Use @classmethod and @staticmethod effectively.
#
#     Add a class variable library_name to Book class.
#
#     Add a class method change_library_name() to update the library_name.
#
#     Add a static method is_valid_year(year) to check if the year is a valid 4-digit number.
#
#     Test the methods by creating some Book objects, changing library name via class method,
#     and validating years with static method.
from operator import truediv


class Book:
    library_name = "central library"
    def __init__(self,title,author,year):
        if not self.is_valid_year(year):
            raise("Year is not valid")
        self.title = title
        self.author = author
        self.year = year
    def display_info(self):
        print(f'---{self.library_name}---')
        print("---Book details---")
        print("Title : ",self.title)
        print("Author: ",self.author)
        print("Year  : ",self.year)
    @classmethod
    def change_library_name(cls,library_name):
        cls.library_name =library_name
    @staticmethod
    def is_valid_year(year):
        if year >= 1999 and year <= 2099 :
            return True
        return False

b1 = Book( "Modern Physics","dr.Verma",2011 )
b2 = Book( "Calculus","sharma",2015 )
b3 = Book( "Algorithms","Cormenn",2012 )
b1.display_info()
b2.display_info()
b3.display_info()
Book.change_library_name("State library")
b1.display_info()
b2.display_info()
b3.display_info()