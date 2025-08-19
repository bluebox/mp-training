import functools
import asyncio

def log_device_state_change(func):
    if asyncio.iscoroutinefunction(func):
        @functools.wraps(func)
        async def wrapper(*args, **kwargs):
            result = await func(*args, **kwargs)
            device = args[0]
            log = f"[{device.get_system_time()}] Device {device.device_id} is now {'ON' if device.is_on else 'OFF'}"
            print(log)
            with open("smart_home_log.txt", "a") as f:
                f.write(log + "\n")
            return result
        return wrapper
    else:
        @functools.wraps(func)
        def wrapper(*args, **kwargs):
            result = func(*args, **kwargs)
            device = args[0]
            log = f"[{device.get_system_time()}] Device {device.device_id} is now {'ON' if device.is_on else 'OFF'}"
            print(log)
            with open("smart_home_log.txt", "a") as f:
                f.write(log + "\n")
            return result
        return wrapper
