# Task 4: Abstraction using Abstract Base Classes
#
# Goal: Learn abstraction by designing abstract base class.
#
#     Use Python’s abc module to create an abstract base class LibraryItem.
#
#     Define abstract methods get_info() and check_availability().
#
#     Make Book inherit LibraryItem and implement these methods.
#
#     Create another class Magazine inheriting LibraryItem and implement the methods differently.
#
#     Instantiate objects and demonstrate polymorphism by calling the same methods on different objects.
from abc import ABC, abstractmethod

class LibraryItem(ABC):
    @abstractmethod
    def get_info(self):
        pass
    @abstractmethod
    def check_availability(self):
        pass

class Book(LibraryItem):
    library_name = "central library"

    def __init__(self, title, author, year):
        self.title = title
        self.author = author
        self.year = year

    @property
    def get_year(self):
        return self.year

    @get_year.setter
    def set_year(self, year):
        if not self.is_valid_year(year):
            raise ValueError("Year is not valid")
        self.year = year

    def display_info(self):
        print(f'---{self.library_name}---')
        print("---Book details---")
        print("Title : ", self.title)
        print("Author: ", self.author)
        print("Year  : ", self.year)

    @classmethod
    def change_library_name(cls, library_name):
        cls.library_name = library_name

    @staticmethod
    def is_valid_year(year):
        if year >= 1999 and year <= 2099:
            return True
        return False
    def get_info(self):
        print("Library name: ",self.library_name)
    def check_availability(self):
        print("Available")

class Magazine(LibraryItem):

    def __init__(self,name):
        self.name=name
    def get_info(self):
        print("Its a magazine: ",self.name)
    def check_availability(self):
        print("Magazines available",self.name)
try:
    book1 = Book( "Modern Physics","dr.Verma",21 )
    book2 = Book( "Calculus","sharma",2015 )
    book3 = Book( "Algorithms","Cormenn",2012 )

    magazine1 = Magazine( "Times2025" )
    magazine2 = Magazine( "Forbes500" )

    book1.display_info()
    book1.get_info()

    magazine1.get_info()
except ValueError as e:
    print(" Incorrect or invalid values: ",e)