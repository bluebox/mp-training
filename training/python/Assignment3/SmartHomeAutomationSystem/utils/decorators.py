import functools
import asyncio

def log_device_state_change(func):
    if asyncio.iscoroutinefunction(func):
        @functools.wraps(func)
        async def async_wrapper(self, *args, **kwargs):
            from SmartHomeAutomationSystem.core.devices import SmartDevice
            before = self.is_on
            result = await func(self, *args, **kwargs)
            after = self.is_on
            if before != after:
                log_msg = f"[{SmartDevice.get_system_time()}] {func.__name__} called on {self.device_id}. State changed: {before} -> {after}"
                print(log_msg)
                with open("smart_home_log.txt", "a") as f:
                    f.write(log_msg + "\n")
            return result
        return async_wrapper
    else:
        @functools.wraps(func)
        def sync_wrapper(self, *args, **kwargs):
            from SmartHomeAutomationSystem.core.devices import SmartDevice
            before = self.is_on
            result = func(self, *args, **kwargs)
            after = self.is_on
            if before != after:
                log_msg = f"[{SmartDevice.get_system_time()}] {func.__name__} called on {self.device_id}. State changed: {before} -> {after}"
                print(log_msg)
                with open("smart_home_log.txt", "a") as f:
                    f.write(log_msg + "\n")
            return result
        return sync_wrapper
