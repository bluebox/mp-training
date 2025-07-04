from core.exceptions import DeviceOfflineError
from functools import wraps
def require_device_on(func):
    @wraps(func)
    def wrapper(self,*args,**kwargs):
        if not self.is_on():
            print("ERROR",DeviceOfflineError())
            return
        func(self,*args,**kwargs)
    return wrapper

def log_device_state_change(func):
    @wraps(func)
    async def wrapper(self, *args, **kwargs):
        previous_state=self.is_on()
        await func(self, *args, **kwargs)
        current_state=self.is_on()
        if current_state!=previous_state:
            if self.is_on():
                with open('/home/developer/PycharmProjects/Python/Assignment_3/smart_home_log.txt', 'a') as f:
                    f.write(f"Device {self._device_id} turned on.\n")
            else:
                with open('/home/developer/PycharmProjects/Python/Assignment_3/smart_home_log.txt', 'a') as f:
                    f.write(f"Device {self._device_id} turned off.\n")
    return wrapper


