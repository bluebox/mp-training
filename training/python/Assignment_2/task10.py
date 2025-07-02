class SmartDevice:
    def __init__(self):
        self._is_on = False

    @property
    def is_on(self):
        return self._is_on
    def turn_on(self):
        self._is_on = True
        print(f"{self.__class__.__name__} is now ON.")

    def turn_off(self):
        self._is_on = False
        print(f"{self.__class__.__name__} is now OFF.")

class SmartLight(SmartDevice):
    def __init__(self,brightness):
        super().__init__()
        self.brightness = brightness
        # self._brightness =  brightness

    @property
    def brightness(self):
        return self.__brightness

    @brightness.setter
    def brightness(self, level):
        # print("setterr")
        if self._is_on and 0 <= level <= 100:
            self.__brightness = level
            print(f"Brightness set to {self.brightness}.")
        else:
            pass
            print("Cannot set brightness;light is off and level is between 0 and 100.")
class SmartThermostat(SmartDevice):
    def __init__(self):
        super().__init__()
        self._temperature = 22 

    @property
    def temperature(self):
        return self._temperature

    @temperature.setter
    def temperature(self, temp):
        if self._is_on and 18 <= temp <= 30:
            self._temperature = temp
            print(f"Temperature set to {self._temperature}°C.")
        else:
            pass
            print("Cannot set temperature;thermostat is ON and temp is between 18 and 30°C.")


light = SmartLight(50)
light.turn_on()
light.brightness = 75
print(light.brightness)
print(light.brightness)

light.brightness = 150
thermostat = SmartThermostat()
thermostat.turn_on()
thermostat.temperature = 25
thermostat.temperature = 35
light.turn_off()
thermostat.turn_off()

light.brightness = 50
thermostat.temperature = 20

light.turn_on()
thermostat.turn_on()

light.brightness = 80
thermostat.temperature = 28

light.turn_off()
thermostat.turn_off()

light.brightness = 50
thermostat.temperature = 20