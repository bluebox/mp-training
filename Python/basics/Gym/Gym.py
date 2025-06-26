class Gym:
    def __init__(self):
        self.__mp = {}  # Corrected constructor

    def addMember(self, a, b):
        self.__mp[a] = b

    def display(self):
        for key, value in self.__mp.items():
            key.display()
            value.display()
