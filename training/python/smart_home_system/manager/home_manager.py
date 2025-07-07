class HomeManager:
    def __init__(self):
        self.__devices={}
        self.all_devices=[]
    def add_devices(self,device_name,id=None):
        if id not in self.__devices:
            new_device=device_name(id)
            self.__devices[id]=new_device
            print("Device added successfully :)")
        else:
            print("This device is already registered")
    def control_device(self, user_role, device_id, action_type, value=None):
        if device_id in self.__devices:
            device_id.perform_action(action_type,value)
        else:
            print("This device does not exist")
    def get_all_device_statuses(self):
        pass
        


