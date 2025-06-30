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
      return count_int 
    counter_func.reset=reset_counter
    return count_int
  return counter_func

# a=create_counter(10)
# print(a())
# print(a.reset())



'''
Task -2 

'''

'''
Problem Statement:
 Create a Python function dynamic_calculator(operation, *numbers, **options) that acts as a flexible calculator.
operation: A string ("add", "subtract", "multiply", "divide").
*numbers: An arbitrary number of numerical arguments (integers or floats) to perform the operation on.
**options: Optional keyword arguments:
initial_value: An initial number to start the calculation with (defaults to 0 for addition/subtraction, 1 for multiplication/division if not provided).
round_result: A boolean (default False). If True, round the final result to 2 decimal places.
safe_division: A boolean (default True). If True for "divide" operation, return "Error: Division by zero" if any divisor is zero. If False, allow ZeroDivisionError to propagate.
The function should return the calculated result or an error message string.
Input:
operation: string
*numbers: variable positional arguments (numbers)
**options: variable keyword arguments (as described above)
Output: Number (int/float) or string error message.

'''

def dynamic_calculator(operation, *numbers, **options):
    round_result = options.get('round_result', False)
    safe_division = options.get('safe_division', True)
    
    if operation in ['add', 'subtract']:
        initial = options.get('initial_value', 0)
    elif operation in ['multiply', 'divide']:
        initial = options.get('initial_value', 1)
    else:
        return "Invalid operation"

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
                    return "All the numbers must be non-zero for this operation"
                else:
                    result /= num  
            else:
                result /= num

    if round_result and isinstance(result, (float, int)):
        result = '{:.2f}'.format(result)

    return result


# print(dynamic_calculator('subtract',1,2,3,4,initial_value=5,round_result=True))
     

'''
Task -3
'''

'''
Problem Statement: Write a Python function get_status_message(value, is_active, limit) that determines a status message based on three inputs:

value (an integer)
is_active (a boolean)
limit (an integer)
The function should return:
"High Alert" if is_active is True AND value is greater than limit.
"Moderate" if is_active is True AND value is less than or equal to limit.
"Inactive" if is_active is False AND value is less than 0 (negative).
"Idle" in all other cases.
Crucially, your solution must primarily use nested ternary operators and logical operators (and, or). Avoid explicit if/elif/else blocks as much as possible for the main logic flow.
Input:
value: integer
is_active: boolean
limit: integer
Output: A string representing the status message.

'''

def get_status_message(value, is_active,limit):
  return "High Alert" if is_active and value>limit else "Moderate" if is_active and value<=limit else "Inactive" if not is_active and value<0 else "Idle"
# print(get_status_message(-1,False,2))

'''
Task -4 
'''

'''
Problem Statement: Write a Python function process_matrix(matrix) that takes a list of lists representing a matrix of integers. The function should iterate through the matrix. If it encounters an even number, it should skip to the next column in the current row. If it encounters a number greater than 10, it should stop processing the current row immediately and move to the next row. For every odd number (that is not greater than 10), add it to a running total. Return the final total.
Input: matrix: A list of lists of integers.
Output: An integer representing the sum of qualifying numbers.

# Input matrix1 = [ [1, 2, 3],
                                [4, 5, 6],
                                [7, 8, 9] ] 
print(f"Matrix 1 Total: {process_matrix(matrix1)}")
Matrix 1 Total: 25

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
Task -5
'''

'''
Problem Statement:
Write a Python function check_mixed_input(data1, data2, data3) that takes three arguments of potentially mixed types. The function should perform a series of checks and return a specific string message based on a complex set of truthy/falsy conditions and indentation rules.
Here's the logic to implement:
If data1 is truthy:
If data2 is falsy:
If data3 is truthy: Return "Stage 1A: Data1 True, Data2 False, Data3 True"
Else: Return "Stage 1B: Data1 True, Data2 False, Data3 False"
Else (data2 is truthy): Return "Stage 1C: Data1 True, Data2 True"
Else (data1 is falsy):
If data3 is falsy:
If data2 is truthy: Return "Stage 2A: Data1 False, Data3 False, Data2 True"
Else: Return "Stage 2B: Data1 False, Data3 False, Data2 False"
Else (data3 is truthy): Return "Stage 2C: Data1 False, Data3 True"
Input: data1, data2, data3: Any Python data types.
Output: A string representing the determined stage.
Examples: 
print(check_mixed_input("hello", [], True))
# Expected: Stage 1A: Data1 True, Data2 False, Data3 True
'''

def check_mixed_input(data1,data2,data3):
   if bool(data1)==True:
      if bool(data2)==False:
         return "Stage 1A: Data1 True, Data2 False, Data3 True" if bool(data3) else "Stage 1B: Data1 True, Data2 False, Data3 False"
      else:
         return  "Stage 1C: Data1 True, Data2 True"
   else:
      if bool(data3)==False:
         return "Stage 2A: Data1 False, Data3 False, Data2 True" if bool(data2) else "Stage 2B: Data1 False, Data3 False, Data2 False"
      else:
         return  "Stage 2C: Data1 False, Data3 True"
      
# print(check_mixed_input("hello", [], True))
# print(check_mixed_input([1], None, False))
# print(check_mixed_input(1, "world", []))
# print(check_mixed_input(0, "active", 1))
# print(check_mixed_input(None, "", 0)) 
# print(check_mixed_input(False, [1, 2], None))


'''
Task -6 
'''

'''
Problem Statement: 
 Write a Python function custom_enumerate_filter(iterable, start=0, step=1, predicate=None) that mimics a flexible enumerate but allows a custom step for the index and an optional predicate function for filtering.
The function should:
Yield (not return) tuples of (index, item).
The index should start at start and increment by step for each item.
If a predicate function is provided, only yield (index, item) pairs for which predicate(item) returns True.
Input:
iterable: Any iterable (list, string, tuple, etc.).
start: An integer, the starting index (default 0).
step: An integer, the increment for the index (default 1).
predicate: An optional function that takes one argument (an item from the iterable) and returns a boolean.
Output: A generator that yields (index, item) tuples.
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

'''
 Problem Statement:
 Write a Python function maze_runner(maze, start_pos, end_pos) that simulates a simple maze solver using a while loop. The maze is a 2D list of characters.
' ' represents an open path.
'#' represents a wall.
'S' is the start (occurs only once).
'E' is the end (occurs only once).
The function should attempt to move from start_pos to end_pos. For simplicity, assume the runner always tries to move: Right -> Down -> Left -> Up (in that order of priority). It can only move to an open path or the end. If it hits a wall or goes out of bounds, it tries the next direction. If all directions are blocked, it considers itself stuck.
The function should return:
The path taken as a list of (row, col) tuples (including start and end).
None if the runner gets stuck and cannot reach the end.
Input:
maze: A list of strings or list of lists of characters.
start_pos: A tuple (row, col) for the starting position.
end_pos: A tuple (row, col) for the ending position.
Output: A list of (row, col) tuples representing the path, or None.
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
        stuck = True

        for dr, dc in directions:
            new_row = current_pos[0] + dr
            new_col = current_pos[1] + dc

            if 0 <= new_row < rows and 0 <= new_col < cols:
                next_cell = maze[new_row][new_col]
                next_pos = (new_row, new_col)

                if next_cell in (' ', 'E') and next_pos not in visited:
                    path.append(next_pos)
                    current_pos = next_pos
                    stuck = False
                    break  

        if stuck:
            return None  
        
# print(maze_runner([[' ','#',' '],[' ',' ','#'],['#',' ','E']],(0,0),(2,2)))


'''
Task -8 
'''

'''
Problem Statement : 
Design a BankAccount class.
The __init__ method should take an account_number and an initial balance. The account_number should be a public attribute, but the balance should be a private attribute (use a convention like _balance or __balance).
Implement deposit and withdraw methods.
The deposit method should add an amount to the balance.
The withdrawal method should subtract an amount from the balance, but only if there are sufficient funds.
Add a get_balance method to retrieve the current balance.
Demonstrate that you cannot directly modify the private _balance attribute from outside the class.

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

'''
 Problem Statement:
Create an abstract Shape class using ABC and abstractmethod from the abc module (though for simplicity, you can initially just define a base class without strict ABC enforcement, and later modify it).
Define a base Shape class with an area method (which should raise NotImplementedError or be an abstract method if using ABC).
Create concrete subclasses Circle and Rectangle that inherit from Shape.
Circle should have a radius and implement the area method.
Rectangle should have width and height and implement the area method.
Add a @classmethod to the Shape class (or one of its subclasses) called describe_shapes that prints a general statement about shapes (e.g., "Shapes are fundamental geometric figures.").
Add a @staticmethod to the Shape class (or one of its subclasses) called get_pi that returns the value of PI (3.14159).
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

'''
 Problem Statement:
 Simulate a smart home system.
Create a SmartDevice base class with a _is_on private attribute and public turn_on() and turn_off() methods. These methods should print a message indicating the device's state change.
Create subclasses SmartLight and SmartThermostat.
SmartLight should have an additional attribute _brightness (private). Implement set_brightness(level) which only works if the light is on and level is between 0 and 100.
SmartThermostat should have an additional attribute _temperature (private). Implement set_temperature(temp) which only works if the thermostat is on and temp is within a reasonable range (e.g., 18-30 Celsius).
Use properties (@property, @setter) to provide controlled access to _brightness and _temperature for SmartLight and SmartThermostat respectively, rather than direct private attribute access. This reinforces controlled access.
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
    self.__brightness=brightness
  @property
  def get_level(self):
     return self.__brightness
  @get_level.setter
  def set_level(self,value):
    self.__brightness=value if 0<value<100 and self._is_on else self.__brightness

class SmartThermostat(SmartDevice):
  def __init__(self,temperature):
    super().__init__(True)
    self.__temperature=temperature
  @property
  def get_temperature(self):
     return self.__temperature
  @get_temperature.setter
  def set_temperature(self,value):
    self.__temperature=value if 18<value<30 and self._is_on else self.__temperature

# light_1=SmartLight(56)
# print(light_1.get_level)
# light_1.set_level=90
# print(light_1.get_level)
# print(SmartThermostat.mro())
# thermo_1=SmartThermostat(22)
# print(thermo_1.get_temperature)
# thermo_1.set_temperature=29
# print(thermo_1.get_temperature)
    

























