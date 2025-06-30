from abc import ABC, abstractmethod


class SmartDevice(ABC):
    _device_count = 0

    def __init__(self, device_id):
        self._device_id = device_id
        self.__is_on = False
        SmartDevice._device_count += 1

    def turn_on(self):
        self.__is_on = True
        print(f"Device {self._device_id} turned on.")

    def turn_off(self):
        self.__is_on = False
        print(f"Device {self._device_id} turned off.")

    def is_on(self):
        return self.__is_on

    @classmethod
    def get_device_count(cls):
        return cls._device_count

    @abstractmethod
    def get_status_report(self):
        pass

    @abstractmethod
    def perform_action(self, action_type, value=None):
        pass


class SmartLight(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__brightness = 0

    def set_brightness(self, level):
        if self.is_on():
            if 0 <= level <= 100:
                self.__brightness = level
                print(f"Brightness of {self._device_id} set to {level}")
            else:
                print("Error: Brightness level must be between 0 and 100.")
        else:
            print("Error: Light must be turned on to set brightness.")

    def get_brightness(self):
        return self.__brightness

    def get_status_report(self):
        return f"SmartLight {self._device_id}: ON={self.is_on()}, Brightness={self.__brightness}"

    def perform_action(self, action_type, value=None):
        if action_type == "set_brightness":
            self.set_brightness(value)
        else:
            print(f"Action {action_type} not supported.")


class SmartThermostat(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__temperature = 20.0

    def set_temperature(self, temp):
        if self.is_on():
            if 18.0 <= temp <= 30.0:
                self.__temperature = temp
                print(f"Temperature of {self._device_id} set to {temp}")
            else:
                print("Error: Temperature must be between 18 and 30 Celsius.")
        else:
            print("Error: Thermostat must be turned on to set temperature.")

    def get_temperature(self):
        return self.__temperature

    def get_status_report(self):
        return f"SmartThermostat {self._device_id}: ON={self.is_on()}, Temperature={self.__temperature}"

    def perform_action(self, action_type, value=None):
        if action_type == "set_temperature":
            self.set_temperature(value)
        else:
            print(f"Action {action_type} not supported.")


class Programmable:
    def schedule_task(self):
        print("Alarm system task scheduled.")


class SecuritySensor(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__is_armed = False

    def arm_sensor(self):
        self.__is_armed = True
        print(f"SecuritySensor {self._device_id} is armed.")

    def get_status_report(self):
        return f"SecuritySensor {self._device_id}: ON={self.is_on()}, Armed={self.__is_armed}"

    def perform_action(self, action_type, value=None):
        if action_type == "arm":
            self.arm_sensor()
        else:
            print(f"Action {action_type} not supported.")


class SmartAlarmSystem(SmartDevice, Programmable):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__armed = False

    def get_status_report(self):
        return f"SmartAlarmSystem {self._device_id}: ON={self.is_on()}, Armed={self.__armed}"

    def perform_action(self, action_type, value=None):
        if action_type == "arm":
            self.__armed = True
            print(f"{self._device_id} armed.")
        elif action_type == "disarm":
            self.__armed = False
            print(f"{self._device_id} disarmed.")
        else:
            print(f"Action {action_type} not supported.")

    def schedule_task(self):
        print(f"{self._device_id} alarm task scheduled.")


class HomeManager:
    def __init__(self):
        self._devices = []

    def add_device(self, device):
        self._devices.append(device)
        print(f"Device {device._device_id} added.")

    def control_device(self, device_id, action_type, value=None):
        for device in self._devices:
            if device._device_id == device_id:
                device.perform_action(action_type, value)
                return
        print(f"No device found with ID {device_id}")

    def get_all_device_statuses(self):
        for device in self._devices:
            print(device.get_status_report())


light = SmartLight("a")
thermostat = SmartThermostat("b")
alarm = SmartAlarmSystem("c")

manager = HomeManager()
manager.add_device(light)
manager.add_device(thermostat)
manager.add_device(alarm)

light.turn_on()
manager.control_device("a", "set_brightness", 80)

thermostat.turn_on()
manager.control_device("b", "set_temperature", 25.5)

alarm.turn_on()
manager.control_device("c", "arm")
alarm.schedule_task()

manager.get_all_device_statuses()

print(HomeManager.__mro__)
print(SmartDevice.__mro__)
print(SecuritySensor.__mro__)
print(Programmable.__mro__)

