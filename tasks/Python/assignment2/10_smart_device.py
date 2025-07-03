class SmartDevice:
    def __init__(self):
        self.__is_on = False

    def turn_on(self):
        self.__is_on = True
        print(f"{self.__class__.__name__} is now ON.")

    def turn_off(self):
        self.__is_on = False
        print(f"{self.__class__.__name__} is now OFF.")

    def is_on(self):
        return self.__is_on


class SmartLight(SmartDevice):
    def __init__(self):
        super().__init__()
        self.__brightness = 0

    def set_brightness(self, level):
        if not self.is_on():
            print("Cannot set brightness. Light is off.")
            return
        if 0 <= level <= 100:
            self.__brightness = level
            print(f"Brightness set to {level}.")
        else:
            print("Brightness must be between 0 and 100.")

    @property
    def brightness(self):
        return self.__brightness

    @brightness.setter
    def brightness(self, level):
        self.set_brightness(level)

class SmartThermostat(SmartDevice):
    def __init__(self):
        super().__init__()
        self.__temperature = 20

    def set_temperature(self, temp):
        if not self.is_on():
            print("Cannot set temperature. Thermostat is off.")
            return
        if 18 <= temp <= 30:
            self.__temperature = temp
            print(f"Temperature set to {temp}°C.")
        else:
            print("Temperature must be between 18 and 30°C.")

    @property
    def temperature(self):
        return self.__temperature

    @temperature.setter
    def temperature(self, temp):
        self.set_temperature(temp)


light = SmartLight()
light.turn_on()
light.brightness = 80
print("Brightness:", light.brightness)

thermo = SmartThermostat()
thermo.turn_on()
thermo.temperature = 25
print("Temperature:", thermo.temperature)