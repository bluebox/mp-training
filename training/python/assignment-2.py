from abc import ABC,abstractmethod
import math

'''
This is Assignment -2 i'm finishing it by monday 
'''


'''
Task -1
'''

'''
Problem Statement:
 Implement a Python function create_counter(initial_count=0) that acts as a factory for counter functions.
create_counter should take an initial_count.
It should then return a nested function called counter_func.
counter_func should, when called, increment an internal count (initialized by initial_count) and return the new count.
Additionally, counter_func should have a nested function reset_counter() that resets the counter back to its initial_count (the one provided when create_counter was first called). This reset_counter function should also be accessible via an attribute of counter_func (e.g., counter_func.reset()).
Input: initial_count: An integer (optional, defaults to 0).
Output: A function (counter_func) that when called increments and returns the count, and also has a .reset() method.
'''


def create_counter(initial_count=0):
  def counter_func():
    nonlocal initial_count 
    count_int=initial_count+1
    def reset_counter():
      nonlocal count_int 
      nonlocal initial_count 
      count_int=initial_count 
    counter_func.reset=reset_counter
    return count_int 
  return counter_func

# a=create_counter(10)
# print(a())



'''
Task -2 

'''


def dynamic_calculator(operation, *numbers, **options):
    round_result = options.get('round_result', False)
    safe_division = options.get('safe_division', True)
    
    if operation in ['add', 'subtract']:
        initial = options.get('initial_value', 0)
    elif operation in ['multiply', 'divide']:
        initial = options.get('initial_value', 1)
    else:
        return "Error: Unsupported operation"

    result = initial

    if operation == "add":
        for num in numbers:
            result += num

    elif operation == "subtract":
        for num in numbers:
            result -= num

    elif operation == "multiply":
        for num in numbers:
            result *= num

    elif operation == "divide":
        for num in numbers:
            if num == 0:
                if safe_division:
                    return "Error: Division by zero"
                else:
                    result /= num  
            else:
                result /= num

    if round_result and isinstance(result, (float, int)):
        result = round(result, 2)

    return result

     

'''
Task -3
'''

def get_status_message(value, is_active,limit):
  return "High Alert" if is_active and value>limit else "Moderate" if is_active and value<=limit else "Inactive" if not is_active and value<0 else "Idle"
# print(get_status_message(-1,False,2))

'''
Task -4 
'''

def process_matrix(matrix):
  running_total=0
  for new_line in matrix:
    for ele in new_line:
      if ele>10:
        break
      elif ele%2!=0:
        running_total+=ele
  return running_total

# def process_matrix(matrix):
#   return sum([ele if ele<10 and ele%2!=0 else break if ele>10 for new_line in matrix for ele in new_line])

# print(process_matrix( [ [1, 2, 3],
#                         [4, 5, 6],  
#                         [7, 8, 9] ]))

'''
Task -6 
'''


def my_func(num):
  return num>0
def custom_enumerate_filter(iter,start,step,predicate=None):
  for index in range(start,len(iter),step):
    if predicate!=None:
      if predicate(iter[index]):
        yield (index,iter[index])
    else:
      yield (index,iter[index])

# for index,value in custom_enumerate_filter([7,5,3,1,10,99,-10,-44,-23,76],1,2,my_func):
#   print(index,value)


'''
Task -7
'''

def maze_runner(maze, start_pos, end_pos):
    rows = len(maze)
    cols = len(maze[0])
    
    visited = set()
    path = [start_pos]
    current_pos = start_pos

    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

    while True:
        if current_pos == end_pos:
            return path 

        visited.add(current_pos)
        moved = False

        for dr, dc in directions:
            new_row = current_pos[0] + dr
            new_col = current_pos[1] + dc

            if 0 <= new_row < rows and 0 <= new_col < cols:
                next_cell = maze[new_row][new_col]
                next_pos = (new_row, new_col)

                if next_cell in (' ', 'E') and next_pos not in visited:
                    path.append(next_pos)
                    current_pos = next_pos
                    moved = True
                    break  

        if not moved:
            return None  


'''
Task -8 
'''

class BankAccount:
  def __init__(self,account_number,balance):
    self.account_number=account_number
    self.__balance=balance
  def deposit(self, amount):
    self.__balance+=amount 
  def withdraw(self,amount):
    self.__balance=self.__balance-amount if amount<=self.__balance else self.__balance
  def getbalance(self):
    return self.__balance
  
# user=BankAccount(23454321,55)
# user.deposit(500)
# print(user.getbalance())
# user.withdraw(100)
# print(user.getbalance())
# user.withdraw(500)
# print(user.getbalance())
# user.__balance=100
# print(user.getbalance())


'''
Task -9
'''
class Shape(ABC):
  @abstractmethod
  def area(self):
    pass

  @classmethod
  def describe_shapes(cls):
    print("Shapes are the building blocks of 3D animation")

  @staticmethod
  def get_pi():
    return math.pi

class Circle(Shape):
  def __init__(self,radius):
    self.radius=radius 
  def area(self):
    return super().get_pi()* math.pow(self.radius,2)
  
class Rectangle(Shape):
  def __init__(self,**kwargs):
    self.width=kwargs["width"]
    self.height=kwargs["height"]
  def area(self):
    return self.width*self.height

# new_circle=Circle(11)
# print(new_circle.area())

# new_rect=Rectangle(width=20,height=10)
# print(new_rect.area())  
  
  
  
'''
Task -10
'''

class SmartDevice:
  def __init__(self,is_on):
    self._is_on=is_on
  def turn_on(self):
    self._is_on=True
    print("The device has been turned on")
  def turn_off(self):
    self._is_on=False
    print("The device has been turned off")

class SmartLight(SmartDevice):
  def __init__(self,brightness):
    super().__init__(True)
    self._brightness=brightness
  def set_level(self,value):
    self._brightness=value if 0<value<100 and self._is_on else self._brightness

class SmartThermostat(SmartDevice):
  def __init__(self,temperature):
    super().__init__(True)
    self._temperature=temperature
  def set_level(self,value):
    self._temperature=value if 18<value<30 and self._is_on else self._temperature
    

























