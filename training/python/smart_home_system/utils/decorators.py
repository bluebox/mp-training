import asyncio
import inspect
from functools import wraps

from core.exceptions import AuthenticationError, DeviceOfflineError


def validate_password(max_attempts=3):
    def decorator(func):
        if inspect.iscoroutinefunction(func):
            @wraps(func)
            async def async_wrapper(self, *args, **kwargs):
                if not self.is_on:
                    print("Device is OFF. Please turn it ON before unlocking.")
                    raise DeviceOfflineError("device is off")
                    return

                attempts = 0
                while attempts < max_attempts:
                    password = input("Enter password: ")
                    if self._validate_password(password):
                        return await func(self, *args, **kwargs)
                    else:
                        print("Invalid password. Try again.")
                        attempts += 1
                print("Maximum attempts exceeded. Access denied.")
                raise AuthenticationError("Max atteptms exceded")
            return async_wrapper
        else:
            @wraps(func)
            def sync_wrapper(self, *args, **kwargs):
                if not self.is_on:
                    print("Device is OFF. Please turn it ON before unlocking.")
                    return

                attempts = 0
                while attempts < max_attempts:
                    password = input("Enter password: ")
                    if self._validate_password(password):
                        return func(self, *args, **kwargs)
                    else:
                        print("Invalid password. Try again.")
                        attempts += 1
                print("Maximum attempts exceeded. Access denied.")
                raise AuthenticationError("Max atteptms exceded")

            return sync_wrapper
    return decorator


def log_device_action():
    from core.devicess import SmartDevice
    def decorator(func):
        if inspect.iscoroutinefunction(func):
            @wraps(func)
            async def async_wrapper(self, *args, **kwargs):
                result = await func(self, *args, **kwargs)
                action_type = args[0] if args else None
                value = args[1] if len(args) > 1 else None
                data = f"{self.__class__.__name__},{self._device_id},{action_type},{SmartDevice.get_system_time()}\n"
                self.update_actions(data)
                return result
            return async_wrapper
        else:
            @wraps(func)
            def sync_wrapper(self, *args, **kwargs):
                result = func(self, *args, **kwargs)
                action_type = args[0] if args else None
                value = args[1] if len(args) > 1 else None
                data = f"{self.__class__.__name__},{self._device_id},{action_type},{SmartDevice.get_system_time()}\n"
                self.update_actions(data)
                return result
            return sync_wrapper
    return decorator
