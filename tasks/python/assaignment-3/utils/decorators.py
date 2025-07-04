import asyncio
from functools import wraps
import inspect
# from core.devices import SmartCamera



def log_device_state_change(func):
    if inspect.iscoroutinefunction(func):
        print("hi")
        @wraps(func)
        async def wrapper(self,*args,**kwargs):
            await func(self,*args,**kwargs)
            with open("logs.txt","w") as log:
                log.write(self.get_status_report())
        return wrapper
    else:
        @wraps(func)
        def wrapper(self,value=None):
            func(value)
            with open("logs.txt","a+") as log:
                log.write(self.get_status_report()+"\n")
        return wrapper

# if __name__ == "__main__":
#     s = SmartCamera("D001")
#     @log_device_state_change
#     def func(self,value=None):
#         asyncio.run(s.turn_on())
#     func()
#     print("end")
