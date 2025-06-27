from abc import  ABC,abstractmethod
class animal(ABC):
    @abstractmethod
    def run(self):
        pass
    def jump(self):
        pass

class dog(animal):
    def run(self):
        print("running")
    def jump(self):
        print("jumping")

a=dog()
a.run()