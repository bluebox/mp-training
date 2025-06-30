class SmartDevice():
    def __init__(self):
        self._is_on = False
    
    def turn_on(self):
        if not self._is_on:
            self._is_on = True
            print(f"{self.__class__.__name__} is ON now.")
        else:
            print(f"{self.__class__.__name__} is already ON.")
    
    def turn_off(self):
        if self._is_on:
            self._is_on = False
            print(f"{self.__class__.__name__} is OFF now.")
        else:
            print(f"{self.__class__.__name__} is already OFF.")
            

class SmartLight(SmartDevice):
    def __init__(self,brightness = 0):
        super().__init__()
        self._brightness = brightness
    
    @property
    def brightness(self):
        return self._brightness
    
    @brightness.setter
    def brightness(self,level):
        self._brightness = level
        
    def set_brightness(self,level):
        if not self._is_on:
            print("light is not on. Brightness level cannot be changed")
        else:
            if not 0<=level<=100:
                print("Brightness level must be between 0 and 100")
            else:
                self.brightness = level
                print(f"Brightness level set to {level}")
                return


class SmartThermostat(SmartDevice):
    def __init__(self,temperature = 22):
        super().__init__()
        self._temperature = temperature
    
    @property
    def temperature(self):
        return self._temperature
    
    @temperature.setter
    def temperature(self,temp):
        self._temperature = temp
        
    def set_temperature(self,temp):
        if not self._is_on:
            print("Thermostat is not on. Temperature cannot be changed")
        else:
            if not 18<=temp<=30:
                print("Temperature must be between 18 and 30")
            else:
                self.brightness = temp
                print(f"Temperature level set to {temp}")
                return

light = SmartLight()
thermostat = SmartThermostat()
light.set_brightness(50)   
light.turn_on()
light.brightness = 75      
light.set_brightness(110)  
thermostat.turn_on()
thermostat.set_temperature(25)
thermostat.temperature = 16  
thermostat.turn_off()
thermostat.set_temperature(23)  

        
        