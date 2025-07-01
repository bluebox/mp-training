class SmartDevice:
    def __init__(self):
        self._is_on = False

    def turn_on(self):
        self._is_on = True
        print(f"{self.__class__.__name__} is now ON.")

    def turn_off(self):
        self._is_on = False
        print(f"{self.__class__.__name__} is now OFF.")

class SmartLight(SmartDevice):
    def __init__(self):
        super().__init__()
        self._brightness = 0

    @property
    def brightness(self):
        return self._brightness

    @brightness.setter
    def brightness(self, level):
        if not self._is_on:
            print("Cannot set brightness. Light is OFF.")
        elif 0 <= level <= 100:
            self._brightness = level
            print(f"Brightness set to {level}%.")
        else:
            print("Brightness level must be between 0 and 100.")

class SmartThermostat(SmartDevice):
    def __init__(self):
        super().__init__()
        self._temperature = 22

    @property
    def temperature(self):
        return self._temperature

    @temperature.setter
    def temperature(self, temp):
        if not self._is_on:
            print("Cannot set temperature. Thermostat is OFF.")
        elif 18 <= temp <= 30:
            self._temperature = temp
            print(f"Temperature set to {temp}°C.")
        else:
            print("Temperature must be between 18 and 30°C.")

light = SmartLight()
light.brightness = 50
light.turn_on()
light.brightness = 120
light.brightness = 70
print("Current brightness:", light.brightness)

thermostat = SmartThermostat()
thermostat.turn_on()
thermostat.temperature = 17
thermostat.temperature = 25
print("Current temperature:", thermostat.temperature)
thermostat.turn_off()
thermostat.temperature = 20
