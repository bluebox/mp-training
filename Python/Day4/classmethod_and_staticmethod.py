class human:
    no_of_bones=206 # this is a class attribute which is same for all objects
    def __init__(self):
        self.name="anand"
    @classmethod
    def display(cls):
        print(cls.no_of_bones)
    @staticmethod
    def print_1_to_10():
        for i in range(11):
            print(i,end=' ')
        print()
human.display()
human.print_1_to_10()

a=human()
a.print_1_to_10()

# @classmethod is accessbile by both class and object but it is not recommend to use
# @classmethod give cls to access the class attribute within the class
# @static are method with cls and self