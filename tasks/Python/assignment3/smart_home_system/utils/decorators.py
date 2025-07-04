
def log_device_state_change(func):
    def wrapper():
        func()
    return wrapper()