"""This module exports the parking dict to csv file"""
import parking_class

def export_csv():
    """This function exports the parking list dict
    to csv file using block file handling"""
    with open('CSV_FILES/Parking_Dict.csv', 'w') as file_csv:
        for key in parking_class.parking.parking_dict:
            for items in parking_class.parking.parking_dict[key]:
                file_csv.write("%s,%s,%s\n"%(key,items.vech_number,items.vech_entrytiming))
