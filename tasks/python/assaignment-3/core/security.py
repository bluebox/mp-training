from core.devices import SmartDevice
from abc import ABC, abstractmethod

class Programmable(ABC):
    @abstractmethod
    def schedule_task(self):
        pass


class SecuritySensor(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self.__is_armed = False

    def is_armed(self):
        return self.__is_armed

    def arm_sensor(self):
        self.__is_armed = True
        print(f"Sensor {self._device_id} armed.")

    def disarm_sensor(self):
        self.__is_armed = False
        print(f"Sensor {self._device_id} disarmed.")

    def get_status_report(self):
        return f"[SecuritySensor] ID: {self._device_id}, ON: {self.is_on}, Armed: {self.__is_armed}"
    def get_supported_actions(self):
        actions = ["arm", "disarm"]
        for i in actions:
            yield i

    def turn_off(self):
        self.__is_armed = False
        super().turn_off()


    def perform_action(self, action_type, value=None):
        if action_type.lower() == "arm":
            self.__is_armed = True
            print(f"Sensor {self._device_id} armed.")
        elif action_type.lower() == "disarm":
            self.__is_armed = False
            print(f"Sensor {self._device_id} disarm.")
        else:
            print(f"Unknown action '{action_type}' for SecuritySensor.")

    def to_dict(self):
        return {"is_on":self.is_on,"arm":self.is_armed}

    def load_state(self,state):
        if state['is_on']:
            self.turn_on()
        else:
            self.turn_off()
        if state["is_armed"]:
            self.arm_sensor()
        else:
            self.disarm_sensor()


class SmartAlarmSystem(SmartDevice, Programmable):
    __sensors = list()
    # device_id = ""

    def __init__(self, device_id):
        super().__init__(device_id)
        # self.device_id = device_id
        self.__sound_volume = 0
        self.__sensors.append(
            SecuritySensor(device_id=device_id + "." + str(len(self.__sensors) + 1)))  # has a relation(loose coupling)

    def add_sensor(self):
        self.__sensors.append(SecuritySensor(device_id=self.device_id + "." + str(len(self.__sensors) + 1)))

    def remove_sensor(self, device_id=None):
        if self.__sensors[0].is_armed():
            print("Alarm System is armed cannot disconnect the sensors.")
            return
        self.__sensors.pop()
        print("A Sensor got Disconnected")

    def get_status_report(self):
        sensor_status = ""
        for sensor in self.__sensors:
            sensor_status += sensor.get_status_report() + "\n"
        return f"[SmartAlarmSystem] ID: {self._device_id}, ON: {self.is_on},\nArmed:" + f"\n{sensor_status}"

    def get_supported_actions(self):
        actions = ["arm", "disarm","set_siren_volume"]

    def perform_action(self, action_type, value=None):
        if action_type.lower() == "arm":
            for sensor in self.__sensors:
                sensor.turn_on()
                sensor.perform_action(action_type, value)
            print(f"Alarm system {self._device_id} armed.")
        elif action_type.lower() == "disarm":
            for sensor in self.__sensors:
                sensor.turn_off()
                sensor.perform_action(action_type, value)
            print(f"Alarm system {self._device_id} disarmed.")
        elif action_type.lower() == "set_siren_volume":
            self.__sound_volume = value
        else:
            print(f"Unknown action '{action_type}' for SmartAlarmSystem.")

    def schedule_task(self):
        print(f"Alarm system {self._device_id} task scheduled.")
        time = SmartDevice.get_system_time()
        arm_schedule_time = time.replace(hour=18, minute=0, second=0, microsecond=0)
        disarm_schedule_time = time.replace(hour=7, minute=0, second=0, microsecond=0)
        if time >= arm_schedule_time:
            self.perform_action("arm")
        elif time >= disarm_schedule_time:
            self.perform_action("disarm")

    def turn_off(self):
        for sensor in self.__sensors:
            sensor.turn_off()
        super().turn_off()

    def to_dict(self):
        dict_rep = {"ON":self.is_on}
        devices = {}
        for sensor in self.__sensors:
            devices[sensor.device_id] = sensor.to_dict()
        dict_rep["sensors"] = devices
        return dict_rep

    def load_state(self,state):
        if state['ON']:
            self.turn_on()
        else:
            self.turn_off()
        devices = dict()
        for i in self.__sensors:
            devices[i.device_id] = i
        for sensor in state["sensors"]:
            if sensor in devices:
                devices[sensor].load_state(state["sensors"][sensor])



