import asyncio
import json
from collections import defaultdict
import re
def is_strong(pwd):
    if pwd is None:
        pass#none value exception raise
    elif not isinstance(pwd, str):
        pass#not a string exception raise
    else:
        pat = fr'^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{{{10},}}$'
        return bool(re.fullmatch(pat, pwd))
def convert_to_json(devices,file_name):
    device_status = dict()
    for device in devices:
        device_status[device.device_id] = device.to_dict()
    status_json = json.dumps(device_status)
    with open(file_name, "w") as f:
        f.write(status_json)


#load class first checks whether the device_id's in the json data are present in the provided devices list. If not present, the following is done
# 1) check if the same class type object present and not written in the device list. If present it overrides the data in that object
# 2) If the above case has failed, It creates the device and populates the details into it.

async def load_class(devices,file_name):#needs clarity
    written_devices = list()
    with open(file_name, "r") as file:
        json_data = json.load(file)
    print(json_data)
    # print(devices)
    c = len(json_data)
    print(c)


    device_dict = {}
    for i in devices:
        device_dict[i.device_id] = i
    written_id = []
    for device_id in json_data:
        if device_id in device_dict:
            if device_dict[device_id].__class__.__name__ == json_data[device_id]["type"]:
                await device_dict[device_id].load_state(json_data[device_id])
                written_devices.append(device_dict[device_id])
                written_id.append(device_id)
            else:
                pass #erro same device id present but different class types
    for device_id in written_id:
        json_data.pop(device_id)
    written_id.clear()
    if len(json_data) ==0:
        await asyncio.sleep(2)
        return devices


    device_dict = defaultdict(list)
    for i in devices:
        if i not in written_devices:
            device_dict[i.__class__.__name__].append(i)
    for device_id in json_data:
        class_name = json_data[device_id]["type"]
        if class_name in device_dict and len(device_dict[class_name])>0:
            await device_dict[class_name][0].load_state(json_data[device_id])
            written_id.append(device_id)
    for device_id in written_id:
        json_data.pop(device_id)
    written_id.clear()
    if len(json_data) == 0:
        await asyncio.sleep(2)
        return devices


    await asyncio.sleep(2)
    raise Exception("No suitable devices found matching the json")



    # while
    #     if device.device_id in json_data:
    #         device.load_state(json_data[device.device_id])
    #         json_data.pop(device.device_id)
    #         devices.remove(device)

    # for device in devices:
    #     print(device.device_id)

if __name__ == "__main__":

    asyncio.run(load_class([],"../manager/home.json"))