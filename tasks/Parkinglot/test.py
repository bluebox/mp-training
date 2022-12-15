#
# parking_dict = {}
# time_dict = {}
# lot_series = ["a", "b", "c", "d"]
# series_sections = [1, 2, 3]
# CAR_AREA = {'A': 10, 'B': 10, 'C': 10, 'D': 10}
# # for i in lot_series:
# #     for j in series_sections:
# #         parking_dict[i, j] = []
# # print(parking_dict)
#
#
# # for obj in main.CAR_List:
# #     print(obj.v_name)
import datetime

a = datetime.datetime.now()
b = datetime.datetime(2022, 11, 11, 18, 25, 30)
c = a - b
print(f'In Time:-{b}')
print(f'Out Time:-{a}')
print('Difference: ', c.total_seconds()/3600)

# hours = c.total_seconds() / 3600
# print('Total difference in hours: ', hours)
# tot_time = int(hours)
# if hours - tot_time > 0.5:
#     tot_time += 1
# if tot_time <= 1:
#     par_charge = 20
# if 1 < tot_time <= 10:
#     par_charge = 20 + (tot_time - 1) * 10
# if tot_time > 10:
#     par_charge = 20 + 100 + (tot_time - 11) * 5
# print(par_charge)
#
#
# # returns the difference of the time of the day
# minutes = c.seconds / 60
# print('Difference in minutes: ', minutes)