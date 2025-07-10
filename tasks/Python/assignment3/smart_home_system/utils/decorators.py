import asyncio
import functools
import datetime

def log_device_state_change(func):

    if asyncio.iscoroutinefunction(func):
        @functools.wraps(func)
        async def async_wrapper(*args, **kwargs):
            result = await func(*args, **kwargs)
            log_message = f"[{datetime.datetime.now()}] {func.__name__} called on device ID: {args[0].device_id}"
            print(log_message)
            with open("smart_home_log.txt", "a") as f:
                f.write(log_message + "\n")
            return result
        return async_wrapper

    else:
        @functools.wraps(func)
        def sync_wrapper(*args, **kwargs):
            result = func(*args, **kwargs)
            log_message = f"[{datetime.datetime.now()}] {func.__name__} called on device ID: {args[0].device_id}"
            print(log_message)
            with open("smart_home_log.txt", "a") as f:
                f.write(log_message + "\n")
            return result
        return sync_wrapper