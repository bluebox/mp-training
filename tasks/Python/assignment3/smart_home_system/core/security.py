from abc import ABC, abstractmethod

from smart_home_system.core.devices import SmartDevice

class Programmable(ABC):
    def schedule_task(self):
        pass

class SecuritySensor(SmartDevice):

    def __init__(self, device_id):
        super().__init__(device_id)
        self.__is_armed = False

    def arm_sensor(self,value):
        if self.is_on:
           print("Security Sensor must be on to arm_sensor")
           return
        elif self.__is_armed:
            if value:
                print(f"Sensor {self._device_id} is already armed.")
                return
            self.__is_armed = value
            print(f"Sensor {self._device_id} is now disarmed.")
        else:
            if value:
                self.__is_armed = value
                print(f"Sensor {self._device_id} is now armed.")
                return
            print(f"Sensor {self._device_id} is already disarmed.")


    @property
    def is_arm(self):
        return self.__is_armed

    @is_arm.setter
    def is_arm(self,value):
        self.arm_sensor(value)

    def get_status_report(self):
        return f"Sensor {self._device_id} , ON: {self.is_on} , Armed: {self.__is_armed}"

    def perform_action(self, action_type, value=None):
        if action_type == "arm_sensor":
            self.arm_sensor(True)
        elif action_type == "disarm_sensor":
            self.arm_sensor(False)
        elif action_type == "get_status_report":
            print(self.get_status_report())
        elif action_type == "turn_on":
            self.turn_on()
        elif action_type == "turn_off":
            self.turn_off()
        else:
            print("Unsupported action for SecuritySensor.")

    def get_supported_actions(self):
        print("Supported actions for Security Sensor are: arm_sensor, disarm_sensor, get_status_report, turn_on, turn_off")

class SmartAlarmSystem(SmartDevice, Programmable):

    def __init__(self, device_id):
        super().__init__(device_id)
        self.__armed = False

    def get_status_report(self):
        return f"Alarm {self._device_id} , ON: {self.is_on} , Armed: {self.__armed}"

    @property
    def arm(self):
        return self.__armed

    #write for property arm to set arm/disarm

    def perform_action(self, action_type, value=None):
        if action_type == "arm":
            self.__armed = True
            print("Alarm armed.")
        elif action_type == "disarm":
            self.__armed = False
            print("Alarm disarmed.")
        elif action_type == "turn_on":
            self.turn_on()
        elif action_type == "turn_off":
            self.turn_off()
        elif action_type == "get_status_report":
            print(self.get_status_report())
        else:
            print("Unsupported action for SmartAlarmSystem.")

    def schedule_task(self):
        print("Alarm system task scheduled.")
        #use time module for schedule alarm
        #take scheduled time as param

    def get_supported_actions(self):

        print("Supported actions for Smart Alarm System are: arm_alarm, disarm_alarm, turn_on, turn_off, get_status_report ")

