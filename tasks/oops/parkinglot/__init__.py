"""parkinglot"""
class Vechile:
    """this class is for vechile attributes"""

    def __init__(self, milage, name, capacity, widthv, depthv, numberv, manufacturer):
        self.milage = milage
        self.name = name
        self.capacity = capacity
        self.widthv = widthv
        self.depthv = depthv
        self.numberv = numberv
        self.manufacturer = manufacturer
class Lot(Vechile):
    """this class is for designing of parking lot"""
    width = 100
    depth = 50
    number = 50
    series = {'A':[60, 30, 10], 'B': [60, 30, 10],
              'C':[60, 30, 10], 'D': [60, 30, 10]}
    status = 'free'
    in_time = 0

    def __init__(self, *args):
        super().__init__(*args)


v1 = Lot(15, "car", 10, 10, 5, 1, "audi")
obj = v1
v2=Lot(20,'bus',10,10,5,1,"ashoka_leyland")
obj=v2
v3=Lot(20,'bike',10,10,5,1,'trumph')
v3.in_time=5
obj=v3


def allocation(obj):
    """this is the function where we allocate/park our vechile"""
    c_r=checkforspace(obj)
    if obj.name=='car':
        j=0
    elif obj.name=='bus':
        j= 1
    else:
        j= 2
    Lot.series[c_r][j]=Lot.series[c_r][j]-obj.widthv
def checkforspace(obj):
    """function to check wheather the vechiles width is sufficient in the lot or not"""
    if obj.name == "car":
        if obj.widthv > 60:
            print('no space available')
            return False
        else:
            c_h = checkseries(obj)
        # print('no space available')
            pass
    if obj.name == "bus":
        if obj.widthv>30:
            print('no space available')
            return False
        else:
            c_h = checkseries(obj)
            pass
    if obj.name== "bike":
        if obj.widthv>10:
            print('no space available')
            return False
        else:
            c_h = checkseries(obj)
            pass            
    return c_h
def checkseries(obj):
    """function to check wheather the series contains required space or not"""
    if obj.name=='car':
        for i in obj.series:
            if obj.series[i][0] >= obj.widthv:
                return i     
    elif obj.name=='bus':
        for i in obj.series:
            if obj.series[i][1] >= obj.widthv:
                return i                     
    else:
        for i in obj.series:
            if obj.series[i][2] >= obj.widthv:
                return i                            
def deallocation(s_e,obj):
    """function for exit of vechiles"""
    if obj.name=='car':
        obj.series[s_e][0]+=obj.widthv
    elif obj.name=='bus':
        obj.series[s_e][1]+=obj.widthv
    else:
        obj.series[s_e][2]+=obj.widthv
def payment(obj):
    """function to estimate fare"""
    out_time=int(input())
    total_parking_time=out_time-obj.in_time
    if total_parking_time>1 and total_parking_time<2:
        print("pay 20 rupees")
    elif total_parking_time>=2 and total_parking_time<=10:
        print("pay",end=' ')
        print(total_parking_time*10+10)    
allocation(obj)
print(obj.series['A'])
deallocation('A',obj)
print(obj.series['A'])
payment(obj)
