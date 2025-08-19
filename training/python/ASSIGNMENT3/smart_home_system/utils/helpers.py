import json

# from zmq.backend.cffi import device

from  ASSIGNMENT3.smart_home_system.manager.home_manager import  HomeManager
from  ASSIGNMENT3.smart_home_system.core.devices import  SmartDevice
from  ASSIGNMENT3.smart_home_system.core.security import  SmartAlarmSystem
from  ASSIGNMENT3.smart_home_system.core.devices import  DeviceRegistrarMeta

class SmartHomeJSONEncoder(json.JSONEncoder):
    def smart_devices_to_json(self, obj,file):
        if isinstance(obj, HomeManager):
            with open(file, "r") as f:
                li = json.load(f)
            for i in obj._devices:
                data = {
                    "__type__": i.__class__.__name__,
                    "device_id": i.device_id,
                    "state": i.get_status_report()
                }

                li.append(data)
            with open(file, "w") as f:
                json.dump(li,f,indent=4)
                    # f.write(data + "\n")
            return


            # return {
            #     "__type__": obj.__class__.__name__,
            #     "device_id": obj.device_id,
            #     "state": obj.get_status_report()
            # }
        return super().default(obj)

    def smart_devices_from_json(self,json_file):
        with open(json_file, "r") as f:
            li = json.load(f)
        devices=[]
        for data in li:
            device_type = data["__type__"]
            device_id = data["device_id"]
            state = data["state"]
            cls = DeviceRegistrarMeta.registry.get(device_type)
            if not cls:
                raise ValueError(f"Unknown device type: {device_type}")
            device = cls(device_id)
            for k, v in state.items():
                if hasattr(device, k):
                    setattr(device, k, v)
            devices.append( device)
        return devices



