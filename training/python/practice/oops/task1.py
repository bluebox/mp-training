# Task 1: Understanding Basics of OOP (Objects, Attributes, Methods, __init__)
#
# Goal: Create a class Book with attributes and methods.
#
#     Define a class Book with attributes: title, author, year.
#
#     Implement an __init__ method to initialize these attributes.
#
#     Add a method display_info() that prints book details in a readable format.
#
#     Create 3 different Book objects and display their information.

class Book:
    def __init__(self,title,author,year):
        self.title = title
        self.author = author
        self.year = year
    def display_info(self):
        print("---Book details---")
        print("Title : ",self.title)
        print("Author: ",self.author)
        print("Year  : ",self.year)


b1 = Book( "Modern Physics","dr.Verma",2011 )
b2 = Book( "Calculus","sharma",2015 )
b3 = Book( "Algorithms","Cormenn",2012 )
b1.display_info()
b2.display_info()
b3.display_info()