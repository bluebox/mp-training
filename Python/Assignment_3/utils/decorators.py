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