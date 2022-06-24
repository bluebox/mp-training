from lot import *


def park(obj, tem):
    if tem == 1:
        if vehicle[0] > obj.v_width * obj.v_depth:
            for i in range(4):
                for j in range(6):

                    if lot[i][j] == 0.0:
                        lot[i][j] = obj
                        vehicle[0] -= obj.v_width * obj.v_depth
                        return
                    if lot[3][5] != 0.0:
                        print('lot is full')
    if tem == 2:
        if vehicle[1] > obj.v_width * obj.v_depth:
            for i in range(4):
                for j in range(6, 7):
                    if lot[i][j] == 0.0:
                        lot[i][j] = obj
                        vehicle[1] -= obj.v_width * obj.v_depth
                        return
                    if lot[3][6] != 0.0:
                        print('lot is full')
    if tem == 3:
        if vehicle[2] > obj.v_width * obj.v_depth:
            for i in range(4):
                for j in range(7, 10):
                    if lot[i][j] == 0.0:
                        lot[i][j] = obj
                        vehicle[2] -= obj.v_width * obj.v_depth
                        return
                    if lot[4][9] != 0.0:
                        print('lot is full')
