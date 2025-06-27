# Task 3: Encapsulation, Private and Public Variables
#
# Goal: Practice encapsulation and understand private/public variables.
#
#     Modify the Book class to make year a private variable (__year).
#
#     Add getter and setter methods (get_year(), set_year()) to access/change the year.
#
#     Ensure setter validates year using the static method from Task 2.
#
#     Try accessing the private variable directly and explain why it’s not recommended.

class Book:
    library_name = "central library"
    def __init__(self,title,author,year):
        self.title = title
        self.author = author
        self.__year=year
    @property
    def get_year(self):
        return self.__year
    
    @get_year.setter
    def set_year(self,year):
        if not self.is_valid_year(year):
            raise ValueError("Year is not valid")
        self.__year=year
        
    def display_info(self):
        print(f'---{self.library_name}---')
        print("---Book details---")
        print("Title : ",self.title)
        print("Author: ",self.author)
        print("Year  : ", self.__year)

    @classmethod
    def change_library_name(cls,library_name):
        cls.library_name =library_name
    @staticmethod
    def is_valid_year(year):
        if year >= 1999 and year <= 2099 :
            return True
        return False
try:
    b1 = Book( "Modern Physics","dr.Verma",2011 )
    b2 = Book( "Calculus","sharma",2015 )
    b3 = Book( "Algorithms","Cormenn",2012 )

    b1.display_info()
    b2.display_info()
    b3.display_info()
except ValueError as e:
    print(" Incorrect or invalid values: ",e)

