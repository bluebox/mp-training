from core.exceptions import DeviceOfflineError
from functools import wraps
def require_device_on(func):
    @wraps(func)
    def wrapper(self,*args,**kwargs):
        if not self.is_on():
            print("ERROR",DeviceOfflineError("This device is offline",666))
            return
        func(self,*args,**kwargs)
    return wrapper

def log_status_change(func):
    @wraps(func)
    async def wrapper(self, *args, **kwargs):
        previous_state=self.is_on()
        await func(self, *args, **kwargs)
        current_state=self.is_on()
        if current_state!=previous_state:
            if self.is_on():
                with open('smart_home_log.txt', 'a') as f:
                    f.write(f"Device {self._device_id} turned on.\n")
            else:
                with open('smart_home_log.txt', 'a') as f:
                    f.write(f"Device {self._device_id} turned off.\n")
    return wrapper